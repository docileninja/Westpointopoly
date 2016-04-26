import { Meteor } from 'meteor/meteor';
import { Template } from 'meteor/templating';
import { ReactiveDict } from 'meteor/reactive-dict';

import { Monopoly, Game } from '../api/monopoly.js';

import { Space, spaceType, HEIGHT, WIDTH } from '../model/Space.js';
import './board.html';

Template.board.onCreated(function boardOnCreated() {
  Meteor.subscribe('monopoly');
});

const render = function(_spaces, players) {
  var canvas = document.getElementById('board');
  var ctx = canvas.getContext('2d');

  canvas.height = HEIGHT + 2;
  canvas.width = WIDTH + 2;

  ctx.fillStyle = '#cde6d0';
  ctx.fillRect(0,0, WIDTH + 1,HEIGHT + 1);
  var playerLocations = {}
  for (const player of players) {
    playerLocations[player.location] = player
  }
  var spaces = [];
  var last;
  for (var i = 0; i < 5; i++) {
    var type = (i == 0 || i == 4) ? spaceType.CORNER : spaceType.TOP;
    const {name, cost} = _spaces[i];
    var next = last ? new Space(type, last.x + last.width, 1, name, cost, playerLocations[i]) : new Space(type, 1, 1, name, cost, playerLocations[i]);
    spaces.push(next);
    last = next;
  }
  for (var i = 5; i < 8; i++) {
    var type = (i == 0 || i == 4) ? spaceType.CORNER : spaceType.TOP;
    const {name, cost} = _spaces[i];
    const next = last ? new Space(type, last.x, last.y + last.height, name, cost, playerLocations[i]) : new Space(type, 1, 1, name, cost, playerLocations[i]);
    spaces.push(next);
    last = next;
  }
  last = new Space(spaceType.CORNER, 1 - last.width, HEIGHT - last.height, '');
  for (var i = 12; i >= 8; i--) {
    var type = (i == 12 || i == 8) ? spaceType.CORNER : spaceType.TOP;
    const {name, cost} = _spaces[i];
    var next = new Space(type, last.x + last.width, last.y, name, cost, playerLocations[i]);
    spaces.push(next);
    last = next;
  }
  last = new Space(spaceType.CORNER, 1, HEIGHT - last.height, '');
  for (var i = 13; i < 16; i++) {
    var type = (i == 12 || i == 8) ? spaceType.CORNER : spaceType.TOP;
    const {name, cost} = _spaces[i];
    var next = new Space(type, last.x, last.y - last.height, name, cost, playerLocations[i]);
    spaces.push(next);
    last = next;
  }
  spaces.forEach((space) => space.draw(ctx));
};

Template.board.helpers({
  spaces() {
    const {spaces, players} = Game();
    render(spaces, players);
    return;
  }
});