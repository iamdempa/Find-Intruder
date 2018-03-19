/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lockme;


/**
 *
 * @author Last Kings
 */
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class GlobalMouseListenerExample implements NativeMouseInputListener {
	public void nativeMouseClicked(NativeMouseEvent e) {
		
	}

	public void nativeMousePressed(NativeMouseEvent e) {
		
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
		          
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		System.out.println("Mouse Moved");
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		
	}

	public static void main(String[] args) {
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		// Construct the example object.
		GlobalMouseListenerExample example = new GlobalMouseListenerExample();

		// Add the appropriate listeners.
		GlobalScreen.addNativeMouseListener(example);
		GlobalScreen.addNativeMouseMotionListener(example);
	}
}
