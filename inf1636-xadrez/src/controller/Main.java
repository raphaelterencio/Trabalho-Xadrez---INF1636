package controller;

import model.ModelAPI;
import view.ViewAPI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main
{	
	static char round_color = 'W';
			
	static boolean isPieceSelected = false;
	static int selected_row = -1;
	static int selected_column = -1;
	
	static int origin_row = -1;
	static int origin_column = -1;
	
	static int backup_row = -1;
	static int backup_column = -1;
	
	static List<int[]> highlighted_path = new ArrayList<>();
	
	private static List<Observer> observers = new ArrayList<>();
	
	public static void main(String[] args)
	{			
		ViewAPI.openWindow();
		ViewAPI.registerObserver();
	
		userLeftClickHandler();
		userRightClickHandler();
		pawnPromotionHandler();
		menuHandler();
	}
	
	// Métodos get()
	
	public static char getRoundColor() { return round_color; }
	
	// Observer
	
    public static void addObserver(Observer obs) { observers.add(obs); }

    public static void removeObserver(Observer obs) { observers.remove(obs); }

    private static void notifyObservers(Event event) 
    {
        for (Observer obs : observers) 
        {
            obs.update(event);
        }
    }
	
	// Callbacks
	
	private static void userLeftClickHandler()
	{
        ViewAPI.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	if (e.getButton() == MouseEvent.BUTTON1) 
            	{
	                int x = e.getX();
	                int y = e.getY();
	                
	                backup_row = selected_row = y / 64;
	                backup_column = selected_column = x / 64;
	                	                
	                if ( !isPieceSelected )
	                {
	                	if (ModelAPI.isThereAPiece(selected_row, selected_column))
	                	{
	                		if ( ModelAPI.getPieceColor(selected_row, selected_column) == round_color )
	                		{
		                		origin_row = selected_row;
		                		origin_column = selected_column;
		                		ViewAPI.highlightPath(selected_row, selected_column);
		                		highlighted_path = ModelAPI.getPossibleMoves(selected_row, selected_column);
		                		isPieceSelected = !isPieceSelected;
	                		}
	                	}
	                }
	                else 
	                {
	                	if ( isHighlighted(selected_row, selected_column) )
	                	{
	                		ModelAPI.movePiece(origin_row, origin_column, selected_row, selected_column);
	            			notifyObservers(Event.getEvent("PIECE_MOVEMENT"));
	            	    	if (ModelAPI.checkPawnPromotion(round_color))
	            				notifyObservers(Event.getEvent("PAWN_PROMOTION"));
	                		round_color = (round_color == 'W') ? 'B' : 'W';
	                		afterMoveProcedures();
	                		selected_row = -1; selected_column = -1;
	                		origin_row = -1; origin_column = -1;
	                		ViewAPI.clearHighlightedPath();
	                	}
	                	else
	                	{
	                		selected_row = -1; selected_column = -1;
	                		origin_row = -1; origin_column = -1;
	                		ViewAPI.clearHighlightedPath();
	                	}
	                	isPieceSelected = !isPieceSelected;
	                }
            	}
            }
        });
    }
	
	private static void userRightClickHandler()
	{
	    ViewAPI.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if (e.getButton() == MouseEvent.BUTTON3) 
	            {
	                saveGame();
	            }
	        }
	    });
	}
	
	private static void pawnPromotionHandler()
	{
		ViewAPI.getMenuItem("Queen").addActionListener(e -> formalizePawnPromotion("Queen"));
		ViewAPI.getMenuItem("Rook").addActionListener(e -> formalizePawnPromotion("Rook"));
		ViewAPI.getMenuItem("Bishop").addActionListener(e -> formalizePawnPromotion("Bishop"));
		ViewAPI.getMenuItem("Horse").addActionListener(e -> formalizePawnPromotion("Horse"));
	}
	
	private static void menuHandler()
	{
		ViewAPI.getButton("NewGame").addActionListener(e -> newGame());
		ViewAPI.getButton("LoadGame").addActionListener(e -> loadGame());
	}
	
	// Auxiliares
	
    private static boolean isHighlighted(int row, int column)
    {
        for (int[] pos : highlighted_path) {
            if (pos[0] == row && pos[1] == column) {
                return true;
            }
        }
        
        return false;
    }
    
    private static void afterMoveProcedures() 
    {	
    	if (ModelAPI.isCheckMate(round_color))
    	{
    		notifyObservers(Event.getEvent("CHECKMATE"));
    		return;
    	}
    	
    	if (ModelAPI.isCheck(round_color))
    		notifyObservers(Event.getEvent("CHECK"));
    	
    	if (ModelAPI.isStaleMate(round_color))
    	{
			notifyObservers(Event.getEvent("STALEMATE"));
			return;
    	}
    } 
    
    private static void formalizePawnPromotion(String piece)
    {
    	ModelAPI.promotePawn(piece, backup_row, backup_column);
    	notifyObservers(Event.getEvent("PAWN_PROMOTED"));
    }
    
    // Estado do jogo
    
    private static void newGame()
    {
		ModelAPI.newGame();
		ViewAPI.showBoard();
    }
    
    private static void loadGame()
    {
    	String game_state = getGameState();
    	
    	if (game_state != null)
    	{
    		ModelAPI.newGame();
    		round_color = ModelAPI.setGameState(game_state);
    		ViewAPI.showBoard();
    	}
    }
    
    private static void saveGame()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar partida");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            // Garante que o arquivo tenha a extensão .txt
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
            }
            
            try (FileWriter writer = new FileWriter(fileToSave)) {
                String gameState = ModelAPI.getGameState();
                
                writer.write(gameState);
                
                System.out.println("Jogo salvo em: " + fileToSave.getAbsolutePath());
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
                // Informar o usuário de um erro
            }
        }
    }
    
    private static String getGameState() 
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Carregar partida");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) 
            {
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) 
                {
                    content.append(line).append("\n");
                }

                return content.toString();
                
            } 
            catch (IOException ex) {}
        }

        return null;
    }
}