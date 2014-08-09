package global;

import play.Application;
import play.GlobalSettings;
import play.Logger;

public class Bootstrap extends GlobalSettings {
	@Override
	public void onStart(Application app) {
		super.onStart(app);

		try {
			init();
		} catch (Exception e) {
			Logger.error("Can't initialize the application!", e);
			cleanup();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onStop(Application app) {
		cleanup();
		super.onStop(app);
	}

	private void init() {
		Registry.init();
	}

	private void cleanup() {
		Registry.destroy();
	}

}
