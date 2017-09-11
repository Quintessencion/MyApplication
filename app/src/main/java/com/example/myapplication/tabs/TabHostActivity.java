package com.example.myapplication.tabs;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class TabHostActivity extends TabActivity {
    //Fields
    final String TABS_TAG_1 = "Tag 5";
    final String TABS_TAG_2 = "Tag 6";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        TabHost tabHost = getTabHost();//этот метод инициализирует TabHost без .setup()
//        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        // инициализация
//        tabHost.setup();

        TabHost.TabSpec tabSpec;

        // создаем вкладку и указываем тег
        tabSpec = tabHost.newTabSpec("tag1");
        // название вкладки
        tabSpec.setIndicator("Вкладка 1");
        // указываем id компонента из FrameLayout, он и станет содержимым
        tabSpec.setContent(R.id.tvTab1);
        // добавляем в корневой элемент
        tabHost.addTab(tabSpec);


        tabSpec = tabHost.newTabSpec("tag2");
        // указываем название и картинку
        // в нашем случае вместо картинки идет xml-файл,
        // который определяет картинку по состоянию вкладки
        tabSpec.setIndicator("Вкладка 2", getResources().getDrawable(R.drawable.tab_icon_selector));
        tabSpec.setContent(R.id.tvTab2);
        tabHost.addTab(tabSpec);


        tabSpec = tabHost.newTabSpec("tag3");
        // создаем View из layout-файла
        View v = getLayoutInflater().inflate(R.layout.tab_header, null);
        // и устанавливаем его, как заголовок
        tabSpec.setIndicator(v);
        tabSpec.setContent(R.id.tvTab3);
        tabHost.addTab(tabSpec);


        tabSpec = tabHost.newTabSpec("tag4");
        tabSpec.setIndicator("Вкладка 4");
        //добавляем содержимое - Activity
        tabSpec.setContent(new Intent(this, OneActivity.class));
        tabHost.addTab(tabSpec);


        //содержимое с помощью TabHost.TabContentFactory
        tabSpec = tabHost.newTabSpec(TABS_TAG_1);
        tabSpec.setContent(tabFactory);
        tabSpec.setIndicator("Вкладка 5");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TABS_TAG_2);
        tabSpec.setContent(tabFactory);
        tabSpec.setIndicator("Вкладка 6");
        tabHost.addTab(tabSpec);

        // вторая вкладка будет выбрана по умолчанию
        tabHost.setCurrentTabByTag("tag2");

        // обработчик переключения вкладок
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    TabHost.TabContentFactory tabFactory = new TabHost.TabContentFactory() {

        @Override
        public View createTabContent(String tag) {
            if (tag.equals(TABS_TAG_1)) {
                return getLayoutInflater().inflate(R.layout.tab, null);
            } else if (tag.equals(TABS_TAG_2)) {
                TextView tv = new TextView(TabHostActivity.this);
                tv.setText("Это создано вручную");
                return tv;
            }
            return null;
        }
    };
}