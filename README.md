# Console-Based Chess Game (Phase 1)

A functional, console-based chess game written in Java. This project supports a two-player game rendered in the terminal, using standard chess notation for movement.

## Environment & Dependencies
* **Language:** Java (JDK 11 or higher)
* **Build Tool:** Standard `javac` compiler 

## How to Run
To compile and run the game from the command line, navigate to the `src` directory and execute the following comand:

```bash
javac game/Game.java
java game.Game
```
## How to Play
Players take turns entering standard chess moves into the console. The format must be `[FROM] [TO]` using standard grid coordinates (Files A-H, Ranks 1-8).

**Example Inputs:**
* Move a piece: `E2 E4`
* Capture a piece `D4 E5`

## Feature List (Phase 1 Checklist)
**Implemented Features:**
- [x] 8x8 Text-based chessboard display with A-H and 1-8 coordinates.
- [x] Turn-based game loop alternating between White and Black.
- [x] Standard chess notation parsing (e.g., "E2 E4").
- [x] Basic move validation (bounds checking, correct player turn).
- [x] Abstract Piece architecture with specific subclasses.

**Not Implemented / Phase 2 Features:**
- [ ] Advanced rules: Castling, En Passant, Pawn Promotion.
- [ ] Check and Checkmate detection logic.

**Known Limitations:**
- Input must include a space between the origin and destination squares.

