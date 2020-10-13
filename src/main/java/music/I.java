package music;

import java.awt.Graphics;

public interface I {
    public interface Hit {
        boolean hit(int x, int y);
    }

    interface Area extends Hit {
        void dn (int x, int y);
        void drag (int x, int y);
        void up (int x, int y);
    }

    interface Show {
        public void show(Graphics g);
    }
}
