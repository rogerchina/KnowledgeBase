package com.debuglife.codelabs.crazyit.chapter14;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ActionListenerAnnotationTest {

    private JFrame mainWin = new JFrame("MainWin");

    @ActionListenerFor(listener = OkActionListener.class)
    private JButton ok = new JButton("ok");

    @ActionListenerFor(listener = CancelActionListener.class)
    private JButton cancel = new JButton("cancel");

    public void init() throws Exception {
	JPanel panel = new JPanel();
	panel.add(ok);
	panel.add(cancel);
	mainWin.add(panel);

	ActionListenerInstaller.processAnnotations(this);
	mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainWin.pack();
	mainWin.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
	new ActionListenerAnnotationTest().init();
    }
}

class OkActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
	JOptionPane.showMessageDialog(null, "clicked ok button.");
    }

}

class CancelActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
	JOptionPane.showMessageDialog(null, "clicked cancel button.");
    }

}

class ActionListenerInstaller {

    public static void processAnnotations(Object obj) throws Exception {
	Class<? extends Object> clazz = obj.getClass();
	for (Field f : clazz.getDeclaredFields()) {
	    f.setAccessible(true);
	    ActionListenerFor alf = f.getAnnotation(ActionListenerFor.class);
	    Object fobj = f.get(obj);
	    if (alf != null && fobj != null && fobj instanceof AbstractButton) {
		Class<? extends ActionListener> listenerClazz = alf.listener();
		ActionListener al = listenerClazz.newInstance();
		AbstractButton ab = (AbstractButton) fobj;
		ab.addActionListener(al);
	    }
	}
    }
}