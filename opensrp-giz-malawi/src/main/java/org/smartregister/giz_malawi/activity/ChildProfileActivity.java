package org.smartregister.giz_malawi.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import org.smartregister.child.util.Utils;
import org.smartregister.giz_malawi.util.GizUtils;
import org.smartregister.view.activity.BaseProfileActivity;

import timber.log.Timber;

public class ChildProfileActivity extends BaseProfileActivity {
    @Override
    protected void attachBaseContext(android.content.Context base) {
        // get language from prefs
        String lang = GizUtils.getLanguage(base.getApplicationContext());
        super.attachBaseContext(GizUtils.setAppLocale(base, lang));
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Utils.showToast(this, "In the profile page!!");
    }

    @Override
    protected void initializePresenter() {
        // Todo
    }

    @Override
    protected ViewPager setupViewPager(ViewPager viewPager) {
        return null;
    }

    @Override
    protected void fetchProfileData() {
        // Todo
    }
}

