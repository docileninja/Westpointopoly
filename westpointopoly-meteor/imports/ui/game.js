import { Meteor } from 'meteor/meteor';
import { Template } from 'meteor/templating';
import { ReactiveDict } from 'meteor/reactive-dict';

import { Monopoly, Game } from '../api/monopoly.js';

import './game.html';
import './board.js';

Template.game.onCreated(function gameOnCreated() {
  this.state = new ReactiveDict();
  Meteor.subscribe('monopoly');
});

if (Meteor.isClient) {
};

Template.game.helpers({
  players() {
    return Game().players;
  },
  turn() {
    return Game().turn;
  },
  currentPlayer() {
    const {turn, players} = Game();
    return players[turn % players.length];
  }
});

Template.game.events({
  'click #join'(e) {
    e.preventDefault();
    console.log('creating/joining game.');
    Meteor.call('monopoly.join');
  },
  'click #move'(e) {
    e.preventDefault();
    console.log('doing move');
    Meteor.call('monopoly.move');
  }
});