package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.MouseAdapter;
import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import java.io.FileWriter;
import java.util.Scanner;
/**
 * The main GUI window for the chess game
 * Displays the 8x8 chess board
 */
public class ChessWindow extends JFrame {

    /**
     * The piece being dragged
     */
    private JLabel draggedPiece = null;

    /**
     * The start of the dragging
     */
    private JPanel startingSquare = null;
    /**
     * The text area that shows the history moves
     */
    private JTextArea moveHistoryArea;
    /**
     * The pannel holding pieces captued by white
     */
    private JPanel whiteCaptures;
    /**
     * The pannel holding pieces captued by black
     */
    private JPanel blackCaptures;
    /**
     * the memory stack for the undo button
     */
    private java.util.Stack<String[]> undoStack = new java.util.Stack<>();

    //constructor
    public ChessWindow() {
        /**
         * it creates the top navigation menu
         */
        JMenuBar menuBar = new JMenuBar();
       
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        /**
         * Sensor to detect clicks on the game button
         * closes the window and makes a new game window
         */
        newGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //closes the window
                ChessWindow.this.dispose();

                //creates a new game window
                ChessWindow freshGame = new ChessWindow();
                freshGame.setSize(900, 600);
                freshGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                freshGame.setVisible(true);
            }
        });

        JMenuItem save = new JMenuItem("Save Game");
        /**
         * sensor to detect clicks on the save button
         * Opens a file browser and saves the game history text to a file
         */
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFileChooser fileBrowser = new JFileChooser();
                // if user clicks Save in the pop-up
                if(fileBrowser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                    try{
                    //open the file
                    FileWriter writer = new FileWriter(fileBrowser.getSelectedFile());
                    //write the history text to the file
                    writer.write(moveHistoryArea.getText());
                    //close the file
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Game saved successfully!");
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Error saving game!");
                    }
                }
            }
        });
        JMenuItem load = new JMenuItem("Load Game");
        /**
         * Sensor to deterct clicks on the Load Game button
         * Opens a file browser and loads the game history text from a file
         */
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFileChooser fileBrowser = new JFileChooser();
                //if the user clicks "Open" in the pop-up
                if(fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    try{
                        //clear out the current sidebar
                        moveHistoryArea.setText("Game History:\n\n");

                        //Set up the scanner to read the file
                        Scanner reader = new Scanner(fileBrowser.getSelectedFile());

                        //read the file lin by line and add it to the sidebar
                        while(reader.hasNextLine()){
                            moveHistoryArea.append(reader.nextLine() + "\n");
                        }
                        reader.close();

                        JOptionPane.showMessageDialog(null, "Game loaded successfully!");
                    } catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Error loading game!");
                    }
                }
            }
        });

        //add items to menu
        gameMenu.add(newGame);
        gameMenu.add(save);
        gameMenu.add(load);

        //add menu to menu bar
        menuBar.add(gameMenu);

        this.setJMenuBar(menuBar);


        /**
         * Sets the main window Layout to use compass regions
         */
        this.setLayout(new java.awt.BorderLayout());
        /**
         * creates the wrapper panel that holds the 8x8 chess board
         */
        JPanel boardPanel = new JPanel(new GridLayout(8,8));

        for (int i = 0; i < 64; i++){
            JPanel square = new JPanel();
            int row = i / 8;
            int col = i % 8;

            if((row + col) % 2 == 0){ // if even then light green else dark green
                square.setBackground(new java.awt.Color(238, 238, 210));
            }
            else{
                square.setBackground(new java.awt.Color(118, 150, 86));
            }

            /**
             * An array of the starting pieces
             * Empty strings are empty squares
             */
            String[] startingPieces = {
                "\u265C", "\u265E ", "\u265D ", "\u265B ", "\u265A ", "\u265D ", "\u265E ", "\u265C ",
                "\u265F ", "\u265F ", "\u265F ", "\u265F ", "\u265F ", "\u265F ", "\u265F ", "\u265F ",
                " ", " ", " ", " ", " ", " ", " ", " ",
                " ", " ", " ", " ", " ", " ", " ", " ",
                " ", " ", " ", " ", " ", " ", " ", " ",
                " ", " ", " ", " ", " ", " ", " ", " ",
                "\u2659 ", "\u2659 ", "\u2659 ", "\u2659 ", "\u2659 ", "\u2659 ", "\u2659 ", "\u2659 ",
                "\u2656 ", "\u2658 ", "\u2657 ", "\u2655 ", "\u2654 ", "\u2657 ", "\u2658 ", "\u2656 "
            };


            //adds the piece sticker to the square
            JLabel pieceSticker = new JLabel(startingPieces[i]); 
            pieceSticker.setFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 40));
            square.add(pieceSticker);

            /**
             * mouse listener. Handles dragging of pieces
             */
            square.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e){
                    startingSquare = (JPanel) e.getSource();
                    //grabs piece if square has a piece
                    if(startingSquare.getComponentCount() > 0){
                        draggedPiece = (JLabel) startingSquare.getComponent(0);
                    }
                }

                public void mouseReleased(MouseEvent e){
                    if (draggedPiece != null) {
                        // Find the wrapper box holding all the squares
                        java.awt.Container boardWrapper = startingSquare.getParent();
                        
                        // Asks the wrapper box which square we dropped the piece on
                        java.awt.Component droppedOn = boardWrapper.getComponentAt(
                            javax.swing.SwingUtilities.convertPoint(startingSquare, e.getPoint(), boardWrapper)
                        );

                        if(droppedOn instanceof JPanel){
                            JPanel targetSquare = (JPanel) droppedOn;
                            
                            String[] snapshot = new String[67];
                            for (int i = 0; i < 64; i++) {
                                JPanel sq = (JPanel) boardWrapper.getComponent(i);
                                if (sq.getComponentCount() > 0) {
                                    snapshot[i] = ((JLabel) sq.getComponent(0)).getText();
                                } else {
                                    snapshot[i] = " ";
                                }
                            }
                            snapshot[64] = moveHistoryArea.getText();
                            
                            String wCaps = "";
                            for(java.awt.Component c : whiteCaptures.getComponents()) wCaps += ((JLabel)c).getText();
                            snapshot[65] = wCaps;
                            
                            String bCaps = "";
                            for(java.awt.Component c : blackCaptures.getComponents()) bCaps += ((JLabel)c).getText();
                            snapshot[66] = bCaps;
                            
                            undoStack.push(snapshot);
                            
                            /**
                             * checks if the target square has a king
                             * if true, display a victory message and end the game
                             */
                            if(targetSquare.getComponentCount() > 0){
                                JLabel capturedLabel = (JLabel) targetSquare.getComponent(0);
                                String pieceText = capturedLabel.getText();
                                if(pieceText.trim().equals("\u2654") || pieceText.trim().equals("\u265A")){
                                    JOptionPane.showMessageDialog(null, "Checkmate! You win!");
                                    System.exit(0);
                                }
                               /**
                                 * Sorts the captured piece into the correct player's capture list.
                                 * Checks if the piece belongs to the White army (Unicode 2654 to 2659).
                                 */
                                String whiteUnicode = "\u2654\u2655\u2656\u2657\u2658\u2659";
                                
                                if (whiteUnicode.contains(pieceText.trim())) {
                                    // Black captured a White piece
                                    blackCaptures.add(capturedLabel); 
                                } else {
                                    // White captured a Black piece
                                    whiteCaptures.add(capturedLabel); 
                                }
                                
                                // Refreshes the sidebars so the pieces appear instantly
                                blackCaptures.revalidate();
                                blackCaptures.repaint();
                                whiteCaptures.revalidate();
                                whiteCaptures.repaint();
                            }

                            targetSquare.removeAll();
                            /**
                             * Takes a 67-slot snapshot of the board, text area, and graveyards
                             */


                            targetSquare.add(draggedPiece);
                            /**
                             * Calculates the target index
                             */
                            int targetIndex = java.util.Arrays.asList(boardWrapper.getComponents()).indexOf(targetSquare);
                            int targetCol = targetIndex % 8;
                            int targetRow = targetIndex / 8;
                            char targetColLetter = (char) ('a' + targetCol);
                            int targetRowNumber = 8 - targetRow;

                            /**
                             * Calculates the starting square
                             */
                            int startIndex = java.util.Arrays.asList(boardWrapper.getComponents()).indexOf(startingSquare);
                            int startCol = startIndex % 8;
                            int startRow = startIndex / 8;
                            char startColLetter = (char) ('a' + startCol);
                            int startRowNumber = 8 - startRow;
                            
                            /**
                             * Logs the piece movement and exact coordinate to the game history
                             */
                            moveHistoryArea.append("Moved: " + draggedPiece.getText().trim() + 
                                                   " from " + startColLetter + startRowNumber + 
                                                   " to " + targetColLetter + targetRowNumber + "\n");
                            
                            // refresh both squares visually
                            targetSquare.revalidate();
                            targetSquare.repaint();
                            startingSquare.revalidate();
                            startingSquare.repaint();
                        }
                        
                        //clear the dragged piece memory
                        draggedPiece = null;
                    }
                }
            });

            /**
             * adds the square to the chessboard wrapper pannel
             */
            boardPanel.add(square);
        }
        /**
         * puts the chessboard wrapper panel into the main window
         */
        this.add(boardPanel, java.awt.BorderLayout.CENTER);
        /**
         * Sets up the game history sidebar on the right side
         */
        moveHistoryArea = new JTextArea("Game History:\n\n");
        moveHistoryArea.setEditable(false);// user can't edit
        
        JScrollPane scrollPane = new JScrollPane(moveHistoryArea);
        // makes sidebar to be 150 pixels wide
        scrollPane.setPreferredSize(new java.awt.Dimension(150, 0));
        /**
         * The undo button that pops the last state and rebuilds the board
         */
        javax.swing.JButton undoButton = new javax.swing.JButton("Undo Move");
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!undoStack.isEmpty()) {
                    String[] lastState = undoStack.pop();
                    
                    // Restores the 64 squares
                    java.awt.Component[] squares = boardPanel.getComponents();
                    for (int i = 0; i < 64; i++) {
                        JPanel sq = (JPanel) squares[i];
                        sq.removeAll();
                        if (!lastState[i].equals(" ")) {
                            JLabel restoredPiece = new JLabel(lastState[i]);
                            restoredPiece.setFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 40));
                            sq.add(restoredPiece);
                        }
                        sq.revalidate();
                        sq.repaint();
                    }
                    
                    // Restores the history text
                    moveHistoryArea.setText(lastState[64]);
                    
                  /**
                     * Restores White's graveyard with size-40 font
                     */
                    whiteCaptures.removeAll();
                    String[] whitePieces = lastState[65].split(" ");
                    for (String piece : whitePieces) {
                        if (!piece.isEmpty()) {
                            JLabel restoredCapture = new JLabel(piece + " ");
                            restoredCapture.setFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 40));
                            whiteCaptures.add(restoredCapture);
                        }
                    }
                    whiteCaptures.revalidate();
                    whiteCaptures.repaint();
                    
                    /**
                     * Restores Black's graveyard with size-40 font
                     */
                    blackCaptures.removeAll();
                    String[] blackPieces = lastState[66].split(" ");
                    for (String piece : blackPieces) {
                        if (!piece.isEmpty()) {
                            JLabel restoredCapture = new JLabel(piece + " ");
                            restoredCapture.setFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 40));
                            blackCaptures.add(restoredCapture);
                        }
                    }
                    blackCaptures.revalidate();
                    blackCaptures.repaint();
                }
            }
        });

        /**
         * Wraps the scroll pane and undo button together
         */
        JPanel historySidebar = new JPanel(new java.awt.BorderLayout());
        historySidebar.add(scrollPane, java.awt.BorderLayout.CENTER);
        historySidebar.add(undoButton, java.awt.BorderLayout.SOUTH);
        historySidebar.setPreferredSize(new java.awt.Dimension(150, 0));
        
        this.add(historySidebar, java.awt.BorderLayout.EAST);

        /**
         * Sets up the captured pieces sidebar on the left side
         */
        JPanel captureSidebar = new JPanel(new GridLayout(2, 1));

        whiteCaptures = new JPanel();
        whiteCaptures.setBorder(javax.swing.BorderFactory.createTitledBorder("White's Captures"));

        blackCaptures = new JPanel();
        blackCaptures.setBorder(javax.swing.BorderFactory.createTitledBorder("Black's Captures"));

        captureSidebar.add(whiteCaptures);
        captureSidebar.add(blackCaptures);

        captureSidebar.setPreferredSize(new java.awt.Dimension(150, 0));
        this.add(captureSidebar, java.awt.BorderLayout.WEST);
    }

    public static void main(String[] args){
        ChessWindow window = new ChessWindow();
        window.setSize(900, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the window
        window.setVisible(true); //makes window visible
    }
}
