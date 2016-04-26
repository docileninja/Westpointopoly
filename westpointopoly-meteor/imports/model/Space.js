export const spaceType = {
  CORNER: 0,
  TOP: 1,
  BOTTOM: 2,
  LEFT: 3,
  RIGHT: 4
};

export const HEIGHT = 800;
export const WIDTH = 800;

export var Space = function (type, x, y, name, cost, player) {
  this.type = type;
  this.x = x;
  this.y = y;
  this.player = player;
  // switch (this.type) {
  //   case spaceType.CORNER:
  //     this.width = WIDTH / 4;
  //     this.height = HEIGHT / 4;
  //     break;
  //   case spaceType.TOP:
  //   case spaceType.BOTTOM:
  //   case spaceType.LEFT:
  //   case spaceType.RIGHT:
  //     this.width = WIDTH / 6;
  //     this.height = HEIGHT / 4;
  //     break;
  // }
  this.name = name;
  this.cost = cost;
  this.width = WIDTH / 5;
  this.height = HEIGHT / 5;
}

const drawTextCentered = function(ctx, text, x, y, font) {
  ctx.save();
  ctx.font = font || '20px serif';
  ctx.fillStyle = '#000';
  const final_x = x - ctx.measureText(text).width / 2;
  ctx.fillText(text, final_x, y);
  ctx.restore();
}

Space.prototype.draw = function(ctx) {
  ctx.save();
  global.ctx = ctx;
  switch (this.type) {
    case spaceType.BOTTOM:
      ctx.rotate(Math.PI);
      break;
    case spaceType.LEFT:
      ctx.rotate(Math.PI / 2);
      break;
    case spaceType.RIGHT:
      ctx.rotate(Math.PI * 3 / 2);
      break;
  }
  ctx.strokeStyle = '#000';
  ctx.strokeRect(this.x, this.y, this.width, this.height);
  drawTextCentered(ctx, this.name, this.x + this.width / 2, this.y + 30);
  if (this.cost) {
    drawTextCentered(ctx, '$' + this.cost, this.x + this.width / 2, this.y + 145);
  }
  console.log(this.player);
  if (this.player) {
    drawTextCentered(ctx, this.player.name, this.x + this.width / 2, this.y + 120);
  }
  ctx.restore();
}