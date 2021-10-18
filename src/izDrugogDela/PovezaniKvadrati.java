package izDrugogDela;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PovezaniKvadrati extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new RectanglePane(), 700, 500);
        primaryStage.setTitle("CS103 - PZ02"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }

    class RectanglePane extends Pane {

        public RectanglePane() {
            this.setOnMousePressed(e -> {
                if (!tackaUnutarPostojeceg(new Point2D(e.getX(), e.getY()))) {
                    
                    //Dodavanje novog kvadrata, dimenzija 50x50
                    getChildren().add(new Rectangle(e.getX()-22, e.getY()-22, 44, 44));
                    obojiUkolikoSuSviPovezani();
                }
            });
        }
        
        //Vraca vrednost "true" ukoliko je tacka iz koje hocemo da nacrtamo novi kvadrat unutar postojeceg
        private boolean tackaUnutarPostojeceg(Point2D p) {
            for (Node kvadrat : this.getChildren()) {
                if (kvadrat.contains(p)) {
                    return true;
                }
            }

            return false;
        }

        //Ukoliko su svi kvadrati povezani, bice obojeni, ukoliko ne, bice beli
        private void obojiUkolikoSuSviPovezani() {
            if (getChildren().isEmpty()) {
                return;
            }
            
            //Kreiranje grana(Edges) grafa
            java.util.List<AbstractGraph.Edge> edges = new java.util.ArrayList<>();
            
            for (int i = 0; i < getChildren().size(); i++) {
                for (int j = i + 1; j < getChildren().size(); j++) {
                    
                    if (preklapanje((Rectangle)(getChildren().get(i)),
                                    (Rectangle)(getChildren().get(j)))) {
                        
                        edges.add(new AbstractGraph.Edge(i, j));
                        edges.add(new AbstractGraph.Edge(j, i));
                    }
                }
            }

            //Kreiranje grafa ciji su cvorovi(Nodes) kvadrati
            Graph<Node> graph = new UnweightedGraph<>((java.util.List<Node>) getChildren(), edges);
            AbstractGraph<Node>.Tree tree = graph.dfs(0); //DFS stablo
            
            boolean daLiSuSviPovezani = getChildren().size() == tree.getNumberOfVerticesFound();

            for (Node kvadrat : getChildren()) {                
                if (daLiSuSviPovezani) { //Ukoliko se kvadrati preklapaju, cine niz, svi su obojeni u istu boju
                    ((Rectangle) kvadrat).setStroke(Color.DARKGRAY);
                    ((Rectangle) kvadrat).setStrokeWidth(4);
                    ((Rectangle) kvadrat).setFill(Color.LIGHTGRAY);
                    
                } else {
                    ((Rectangle) kvadrat).setStroke(Color.BLACK);
                    ((Rectangle) kvadrat).setStrokeWidth(2);
                    ((Rectangle) kvadrat).setFill(Color.color(Math.random(), Math.random(), Math.random(), 0.8));
                }
            }
        }
    }

    public static boolean preklapanje(Rectangle kvadrat1, Rectangle kvadrat2) {
        
        return kvadrat1.getX() < kvadrat2.getX() + kvadrat2.getWidth()
                && kvadrat2.getX() < kvadrat1.getX() + kvadrat1.getWidth()
                && kvadrat1.getY() < kvadrat2.getY() + kvadrat2.getHeight()
                && kvadrat2.getY() < kvadrat1.getY() + kvadrat1.getHeight();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
