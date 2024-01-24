package dad.calculadora.css.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CalcApp extends Application {
	
	private CalcController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new CalcController();
		
		primaryStage.setTitle("Calculadora");
		primaryStage.getIcons().add(new Image("/images/calculator-32x32.png"));
		primaryStage.setScene(new Scene(controller.getView(), 300, 300));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
