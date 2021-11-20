package pantallas;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import dominio.Video;
import tds.video.VideoWeb;

public class ImgTabla extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
        if(o!=null) {
            VideoWeb vWeb = FrameBase.getVideoWeb();
            Video video = (Video) o;
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setIcon(vWeb.getThumb(video.getUrl())); //esto no va como Video, lo cual no entiendo por que
            label.setText(video.getTitulo());
        }
        else return null;
        return label;

    }
}