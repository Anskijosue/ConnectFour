package gameFour;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/******************************************************************************
 * Compilation: javac Play.java
 *  Execution: not stand alone
 *   Dependencies: JavaFx
 * This class plays a connect four game, Rick and Morty style!
 *
 ******************************************************************************/

public class Play extends Application {
	ConnectGame board = new ConnectGame();
	private static int current = 1;
	Rick computer = new Rick();
	boolean firstplayer = false;
	Button button1 = new Button("Play a computer");

	public void start(Stage primaryStage) {
		// intro
		button1.setDisable(true);
		Label introtext = new Label("Click Rick or Morty to play against the Machine.");
		introtext.relocate(150, 40);
		Image rickright1 = new Image("file:rickback.png");
		ImageView rickright = new ImageView(rickright1);
		Image mortyleft1 = new Image("file:mortyback.png");
		ImageView mortyleft = new ImageView(mortyleft1);
		Image introback1 = new Image("file:R&M.png");
		ImageView introback = new ImageView(introback1);
		primaryStage.setTitle("Captain's Mistress. R&M style");
		Pane pane1 = new Pane();
		rickright.relocate(500, 25);
		mortyleft.relocate(0, 189);
		rickright.setOpacity(0.5);
		mortyleft.setOpacity(0.5);
		rickright.setOnMousePressed(e -> {
			firstplayer = true;
			rickright.setOpacity(1);
			mortyleft.setOpacity(0.5);
			String musicrick = "Wubba.mp3";
			Media musicrick1 = new Media(new File(musicrick).toURI().toString());
			MediaPlayer wubbalub = new MediaPlayer(musicrick1);
			wubbalub.play();
			button1.setText("Play Against a Morty");
			button1.setDisable(false);
			introtext.setOpacity(0);
		});
		mortyleft.setOnMousePressed(e -> {
			firstplayer = false;
			rickright.setOpacity(0.5);
			mortyleft.setOpacity(1);
			String musicmorty = "Geez.mp3";
			Media musicmorty1 = new Media(new File(musicmorty).toURI().toString());
			MediaPlayer maman = new MediaPlayer(musicmorty1);
			maman.play();
			button1.setText("Play Against a Rick");
			button1.setDisable(false);
			introtext.setOpacity(0);
		});
		Button button2 = new Button("Play Against a Human");
		button1.relocate(145, 250);
		button2.relocate(320, 250);
		pane1.getChildren().add(introback);
		pane1.getChildren().add(button1);
		pane1.getChildren().add(button2);
		pane1.getChildren().add(mortyleft);
		pane1.getChildren().add(rickright);
		pane1.getChildren().add(introtext);
		Scene introScene = new Scene(pane1, 620, 350);
		primaryStage.setScene(introScene);
		primaryStage.show();

		// Setup

		// winner1
		Pane pane3 = new Pane();
		Button newbut = new Button("Play Again");
		Scene winner1 = new Scene(pane3, 550, 550);
		Image winnerrick2 = new Image("file:rickwon.png");
		ImageView winnerrick = new ImageView(winnerrick2);
		pane3.getChildren().add(winnerrick);
		newbut.relocate(395, 168);
		pane3.getChildren().add(newbut);

		// winner2
		Pane pane4 = new Pane();
		Scene winner2 = new Scene(pane4, 600, 750);
		Button newbut2 = new Button("Play Again");
		newbut2.relocate(400, 150);
		Image winnermorty2 = new Image("file:mortywon.png");
		ImageView winnermorty = new ImageView(winnermorty2);
		pane4.getChildren().add(winnermorty);
		pane4.getChildren().add(newbut2);

		// Stage 2 (Against computer)
		Image background3 = new Image("file:background.png");
		ImageView background2 = new ImageView(background3);
		ImageView backgroundx = new ImageView(background3);
		Pane pane2 = new Pane();
		pane2.getChildren().add(backgroundx);
		Scene gameScene = new Scene(pane2, 800, 800);
		button1.setOnAction(e -> {
			computer.init(false);
			primaryStage.setFullScreen(true);
			primaryStage.setScene(gameScene);
			if (!firstplayer) {
				int x3 = computer.move();
				int y3 = board.theYvalue2(x3);
				int x4 = ConnectGame.getTheX3(x3) - 50;
				int y4 = board.getTheY(x3) - 25;
				Image rick1 = new Image("file:rick.png");
				ImageView rick = new ImageView(rick1);
				rick.relocate(x4, y4);
				pane2.getChildren().add(rick);
				board.makeMove2(x3, y3);
			}

		});
		pane2.setOnMouseClicked(e -> {
			{
				ifloop: if (board.isValid(e.getX())) {
					int x = ConnectGame.getTheX2(e.getX()) - 50;
					int y = board.getY(x) - 25;
					if (firstplayer) {
						Image rick1 = new Image("file:rick.png");
						ImageView rick = new ImageView(rick1);
						rick.relocate(x, y);
						pane2.getChildren().add(rick);
					} else {
						Image morty1 = new Image("file:morty.png");
						ImageView morty = new ImageView(morty1);
						morty.relocate(x, y);
						pane2.getChildren().add(morty);
					}
					int x2 = ConnectGame.getTheX(e.getX());
					int y2 = board.theYvalue(e.getX());
					board.makeMove(x2, y2);
					if (board.hasWon()) {
						primaryStage.setScene(winner1);
						break ifloop;
					}
					computer.inform(x2);
					int x3 = computer.move();
					int y3 = board.theYvalue2(x3);
					int x4 = ConnectGame.getTheX3(x3) - 50;
					int y4 = board.getTheY(x3) - 25;
					board.makeMove2(x3, y3);
					if (board.hasWon()) {
						primaryStage.setScene(winner2);
						break ifloop;
					}
					if (!firstplayer) {
						Image rick1 = new Image("file:rick.png");
						ImageView rick = new ImageView(rick1);
						rick.relocate(x4, y4);
						pane2.getChildren().add(rick);
					} else {
						Image morty1 = new Image("file:morty.png");
						ImageView morty = new ImageView(morty1);
						morty.relocate(x4, y4);
						pane2.getChildren().add(morty);
					}
					primaryStage.setScene(gameScene);
				}
			}
		});
		// Against a human.
		Pane pane5 = new Pane();
		pane5.getChildren().add(background2);
		Scene gameScene2 = new Scene(pane5, 800, 800);
		button2.setOnAction(e -> {
			primaryStage.setFullScreen(true);
			primaryStage.setScene(gameScene2);
		});
		pane5.setOnMouseClicked(e -> {
			{
				if (board.isValid(e.getX())) {
					int x = ConnectGame.getTheX2(e.getX()) - 50;
					int y = board.getY(x) - 25;
					if (current == 1) {
						Image rick1 = new Image("file:rick.png");
						ImageView rick = new ImageView(rick1);
						rick.relocate(x, y);
						pane5.getChildren().add(rick);
						int x2 = ConnectGame.getTheX(e.getX());
						int y2 = board.theYvalue(e.getX());
						board.makeMove(x2, y2);
						primaryStage.setScene(gameScene2);
						current++;
						if (board.hasWon()) {
							primaryStage.setScene(winner1);
						}
					} else {
						Image morty1 = new Image("file:morty.png");
						ImageView morty = new ImageView(morty1);
						morty.relocate(x, y);
						pane5.getChildren().add(morty);
						int x2 = ConnectGame.getTheX(e.getX());
						int y2 = board.theYvalue(e.getX());
						board.makeMove2(x2, y2);
						primaryStage.setScene(gameScene2);
						current--;
						if (board.hasWon()) {
							primaryStage.setScene(winner2);
						}
					}
				}
			}
		});
		newbut.setOnAction(e -> {
			board = new ConnectGame();
			Image background4 = new Image("file:background.jpg");
			ImageView background1 = new ImageView(background4);
			pane5.getChildren().clear();
			pane2.getChildren().clear();
			pane5.getChildren().add(background1);
			pane2.getChildren().add(background1);
			primaryStage.setScene(introScene);
			current = 1;
			computer = new Rick();
		});
		newbut2.setOnAction(e -> {
			board = new ConnectGame();
			Image background4 = new Image("file:background.jpg");
			ImageView background1 = new ImageView(background4);
			pane5.getChildren().clear();
			pane2.getChildren().clear();
			pane5.getChildren().add(background1);
			pane2.getChildren().add(background1);
			primaryStage.setScene(introScene);
			current = 1;
			computer = new Rick();
		});

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
