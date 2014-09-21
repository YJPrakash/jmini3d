package jmini3d.gwt;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import jmini3d.Scene;
import jmini3d.SceneController;

public class EntryPoint3d implements EntryPoint, SceneController {

	public Canvas3d canvas3d;

	public void onModuleLoad() {
		canvas3d = new Canvas3d("./", this, true);

		Window.addResizeHandler(new ResizeHandler() {
			public void onResize(ResizeEvent event) {
				canvas3d.setSize(event.getWidth(), event.getHeight());
			}
		});

		onCreateSetContentView();

		canvas3d.setSize(Window.getClientWidth(), Window.getClientHeight());
		canvas3d.onResume();
	}

	protected void onCreateSetContentView() {
		appendToBody(canvas3d.getElement());
	}

	public static native void appendToBody(Element element) /*-{
		$doc.body.appendChild(element);
	}-*/;

	public Scene getScene(int width, int height) {
		return null;
	}

	native void log(String message) /*-{
		console.log(message);
    }-*/;
}