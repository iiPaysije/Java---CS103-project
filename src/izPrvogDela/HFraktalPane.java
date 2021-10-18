package izPrvogDela;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class HFraktalPane extends Pane {
    
        double lastSize;
        int stepen;
        double w;
        double h;

        HFraktalPane() {
            w = 600;
            h = 600;
            lastSize = Math.min(w, h) * 0.4;
            setMinSize(w, h);
            crtaj();
        }

        public void crtaj() {

            getChildren().clear();
            double x = w * 0.30;
            double y = h * 0.70;
            crtaj(x, y, stepen, lastSize);
        }

        private void crtaj(double x, double y, int stepen, double lastSize) {

            Line l1 = new Line(x, y, x, y - lastSize);
            Line l2 = new Line(x + lastSize, y, x + lastSize, y - lastSize);
            Line l3 = new Line(x, y - (lastSize / 2), x + lastSize, y - (lastSize / 2));
            getChildren().addAll(l1, l2, l3);

            if (stepen >= 1) {
                double halfSize = lastSize / 2;
                double offset = halfSize / 2;
                //gore levo, gore desno H
                crtaj(l1.getStartX() - offset, l1.getEndY() + halfSize / 2, stepen - 1, halfSize);
                crtaj(l2.getStartX() - offset, l1.getEndY() + halfSize / 2, stepen - 1, halfSize);
                //dole levo, dole desno H
                crtaj(l1.getEndX() - offset, l1.getStartY() + halfSize / 2, stepen - 1, halfSize);
                crtaj(l2.getEndX() - offset, l1.getStartY() + halfSize / 2, stepen - 1, halfSize);
            } else if (stepen == 0) {
                getChildren().removeAll(l1, l2, l3);
            }
        }

        public void postaviStepen(int stepen) {
            this.stepen = stepen;
        }
}
