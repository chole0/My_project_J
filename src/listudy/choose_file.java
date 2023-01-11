package listudy;

import javax.swing.*;
import java.awt.*;

public class choose_file extends Component {
    private void uploadFileActionPerformed(java.awt.event.ActionEvent evt) {
//打开文件选择对话框
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择上传文件");
        fileChooser.setApproveButtonText("选择");
        int choice = fileChooser.showOpenDialog(this); //显示对话框
        if (choice == JFileChooser.APPROVE_OPTION) { //点击选择按钮File file=fileChooser.getSelectedFile();//获取文件对象
        }
    }
}
