import { Meteor } from 'meteor/meteor';
import { Mongo } from 'meteor/mongo';
import { check } from 'meteor/check';

export const Monopoly = new Mongo.Collection('monopoly');

if (Meteor.isServer) {
  	Meteor.publish('monopoly', function tasksPublication() {
	    return Monopoly.find({
	    	players: { $elemMatch: { id: this.userId } }
		});
  	});
}

let Space = function(name, color, cost, isProperty, ownerId) {
	return {
		name, color, cost, isProperty, ownerId
	}
}

export const Game = () => Monopoly.findOne({
  active: true,
  players: { $elemMatch: { id: Meteor.userId() } }
});

Meteor.methods({
	'monopoly.create'() {
		if (! Meteor.userId()) {
			throw new Meteor.Error('not-authorized');
		}

		Monopoly.insert({
			turn: 0,
			active: false,
			players: [
				{
					location: 0,
					money: 500,
					id: Meteor.userId(),
					name: Meteor.user().username || 'Player X',
					active: true
				}
			],
			spaces: [
				Space('Go', 'green', 0, false, undefined),
				Space('Thayer Bookstore', 'yellow', 50, true, undefined),
				Space('C-store', 'yellow', 100, true, undefined),
				Space('Company Store', 'yellow', 80, true, undefined),
				Space('Hours', 'green', 0, false, undefined),
				Space('Jefferson Hall', 'green', 100, true, undefined),
				Space('Bartlett Hall', 'green', 120, true, undefined),
				Space('Thayer Hall', 'green', 150, true, undefined),
				Space('DCA', 'green', 0, false, undefined),
				Space('Mess Hall', 'red', 50, true, undefined),
				Space('Grant Hall', 'red', 200, true, undefined),
				Space('The Firstie', 'red', 120, true, undefined),
				Space('Go to Hours', 'green', 0, false, undefined),
				Space('Tasking', 'green', 0, false, undefined),
				Space('Hayes', 'gray', 100, true, undefined),
				Space('Arvin', 'gray', 300, true, undefined)
			]
		});
	},
	'monopoly.join'() {
		if (!Meteor.userId()) {
			throw new Meteor.Error('not-authorized');
		}

		const game = Monopoly.findOne({
			active: false,
			$where: 'this.players.length < 4'
		});

		if (game) {
			Monopoly.update({
				active: false,
				$where: 'this.players.length < 4'
			}, { $push: { players: {
				location: 0,
				money: 500,
				id: Meteor.userId(),
				name: Meteor.user().username || 'Player X',
				active: true
			} } })
			if (game.players.length >= 3) Monopoly.update({
				active: false,
				$where: 'this.players.length <= 4'
			}, { $set: { active: true } });
		} else {
			Meteor.call('monopoly.create');
		}

		
	},
	'monopoly.move'() {
		const game = Monopoly.findOne({
			players: { $elemMatch: { id: Meteor.userId() } },
			active: true
		});
		const players = game.players;
		const turn = game.turn % players.length;
		const currentPlayer = game.players[turn];

		if (currentPlayer.id !== Meteor.userId()) {
		 	throw new Meteor.Error('not-authorized');
		}

		if (currentPlayer.active === true) {
			const lastLoc = currentPlayer.location;
			const roll = Math.floor(Math.random() * 5) + 1;
			const nextLoc = lastLoc + roll;
			const spaces = game.spaces;

			if (nextLoc % spaces.length < lastLoc) currentPlayer.money += 200;
			currentPlayer.location = nextLoc % spaces.length;

			const currentSpace = spaces[currentPlayer.location];
			if (currentSpace.isProperty) {
				if (currentSpace.ownerId == undefined) {
					currentSpace.ownerId = currentPlayer.id;
					currentPlayer.money -= currentSpace.cost;
				} else {
					const spaceOwner = players.find((player) => player.id == currentSpace.ownerId);
					spaceOwner.money += currentSpace.cost / 2;
					currentPlayer.money -= currentSpace.cost / 2;
				}
			}
			

			if (currentPlayer.money < 0) currentPlayer.active = false;
		}

		game.turn += 1;
		Monopoly.update({
			players: { $elemMatch: { id: Meteor.userId() } },
			active: true
		}, game);
	}
});