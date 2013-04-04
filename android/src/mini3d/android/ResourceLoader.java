package mini3d.android;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ResourceLoader {

	Context context;

	HashMap<String, Bitmap> customBitmaps = new HashMap<String, Bitmap>();

	public ResourceLoader(Context ctx) {
		this.context = ctx;
	}

	public Bitmap getImage(String image) {
		if (customBitmaps.containsKey(image)) {
			return customBitmaps.get(image);
		}

		String uri = "drawable/" + image;
		int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

		return makeBitmapFromResourceId(imageResource);
	}

	public Bitmap makeBitmapFromResourceId(int id) {
		InputStream is = context.getResources().openRawResource(id);

		Bitmap bitmap;
		try {
			bitmap = BitmapFactory.decodeStream(is);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// Ignore.
			}
		}
		return bitmap;
	}

	public void freeBitmap(String name, Bitmap bitmap) {
		bitmap.recycle();
		if (customBitmaps.containsKey(name)) {
			customBitmaps.remove(name);
		}
	}

	public void addCustomBitmap(String name, Bitmap bitmap) {
		customBitmaps.put(name, bitmap);
	}

	public Context getContext() {
		return context;
	}
}