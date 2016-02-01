# WestPointopoly

## Description

This game is a simplified version of the classic game of monopoly. The classic monopoly paradigms would be tailored to West Point traditions. For instance, instead of jail one would walk hours instead. Local restaurants such as Schades' and American Burrito could replace the railroads. Arvin and Hayes would replace the utilities. For the sake of simplicity, I will describe the tiles with traditional names first, and rename them after the game is functional. All game balance issues will be subject to frequent change throughout development.

## Equipment

The game will have several components including the game board, dice, player pieces, property titles, and game currency. The game board will consist of a square with 16 spaces. The corner spaces (from the top left corner clockwise) are: GO, jail, free parking, and 'go to jail'. All spaces inbetween the corners are properties spaces except for the one immediately after 'go to jail', which is a chance space. The initial pieces will be replaced by an abbreviation next to each players name, which signifies which tile they are on. Each player's name will be visible to each other player and their currency will be displayed next to their name e.g. 'Adam ($500) - GO'. The Property pieces owned by a player will be displayed under their name followed by its face value. e.g. 'Boardwalk - $500' The dice are two randomly generated numbers from 1-4. They will be displayed as '(d1+d2) = sum' All information in the game will be visible to all other players.

## Rules

Monopoly is played by 2 - 4 players. Each player recieves a starting amount of money (currently $1500, depending on game balance) All players start on the 'GO' tile. The game begins with a player rolling the dice and moving their piece that many spaces clockwise. They will land on a space and perform actions as directed by the space. If a player rolls doubles, they must take an additional turn. If they roll 3 doubles in a row, they must go to jail.

### Spaces
When a player lands on an unowned property, they have to opportunity to buy it (prices will vary but will increase around the board). If the property is already owned they must pay rent to the owner for half of the purchase price. If all the properties along a side of the board are owned by a single player the rent is the purchase price. If the space is not a property they will simply follow the instructions on the board. The Jail space and free parking do nothing. The 'go to jail' space will move the player to Jail. The chance space (immediately after 'go to jail') will result in a random number being generated with a 1 yeilding $50 for the player, and a 2 resulting in a $50 penalty.

Jail

Players in jail have the choice to attempt to roll doubles or pay $50. If they choose to attempt doubles, they get 3 rolls. Should they succeed in rolling a pair, they are no longer 'in jail' and will take a turn as normal the following turn. Eventually there will be a 3 turn maximum on attempting to roll doubles, as players can effectively never lose the game if they decide to stay in jail forever.

Whenever the player passes GO they are payed $200. If a player's on hand money reaches 0 or negative they are out. Play continues until one player remains.

When a player is out of the game, their properties remain active, but all rent goes to the bank.