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
                freshGame.setSize(600, 600);
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
                            }

                            targetSquare.removeAll();
                            targetSquare.add(draggedPiece);
                            /**
                             * Calculates the square index
                             */
                            int squareIndex = java.util.Arrays.asList(boardWrapper.getComponents()).indexOf(targetSquare);
                            int col = squareIndex % 8;
                            int row = squareIndex / 8;
                            char collecter = (char) ('a' + col);
                            int rowNumber = 8 - row;
                            /**
                             * logs the piece movement and exact coordinate to the game history
                             */
                            moveHistoryArea.append("Moved: " + draggedPiece.getText().trim() + " to " + collecter + rowNumber + "\n");
                            
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
        this.add(scrollPane, java.awt.BorderLayout.EAST);
    }

    public static void main(String[] args){
        ChessWindow window = new ChessWindow();
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the window
        window.setVisible(true); //makes window visible
    }
}
