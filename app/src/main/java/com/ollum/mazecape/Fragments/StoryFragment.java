package com.ollum.mazecape.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.R;

import java.util.ArrayList;

import static com.ollum.mazecape.Activities.MainActivity.volumeMusic;

public class StoryFragment extends Fragment {

    public static MediaPlayer storyBGM;
    LinearLayout linearLayout;
    TextView chapter1, chapter2, chapter3, chapter4;
    TextView chapter1page1, chapter1page2, chapter1page3, chapter1page4, chapter1page5, chapter1page6, chapter1page7, chapter1page8, chapter1page9, chapter1page10;
    TextView chapter2page1, chapter2page2, chapter2page3, chapter2page4, chapter2page5, chapter2page6, chapter2page7, chapter2page8, chapter2page9, chapter2page10;
    TextView chapter3page1, chapter3page2, chapter3page3, chapter3page4, chapter3page5, chapter3page6, chapter3page7, chapter3page8, chapter3page9, chapter3page10;
    TextView chapter4page1, chapter4page2, chapter4page3, chapter4page4, chapter4page5, chapter4page6, chapter4page7, chapter4page8, chapter4page9, chapter4page10;
    ArrayList<TextView> chapter1list, chapter2list, chapter3list, chapter4list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_help);

        chapter1 = (TextView) view.findViewById(R.id.chapter_1);
        chapter2 = (TextView) view.findViewById(R.id.chapter_2);
        chapter3 = (TextView) view.findViewById(R.id.chapter_3);
        chapter4 = (TextView) view.findViewById(R.id.chapter_4);

        chapter1page1 = (TextView) view.findViewById(R.id.chapter_1_page_1);
        chapter1page2 = (TextView) view.findViewById(R.id.chapter_1_page_2);
        chapter1page3 = (TextView) view.findViewById(R.id.chapter_1_page_3);
        chapter1page4 = (TextView) view.findViewById(R.id.chapter_1_page_4);
        chapter1page5 = (TextView) view.findViewById(R.id.chapter_1_page_5);
        chapter1page6 = (TextView) view.findViewById(R.id.chapter_1_page_6);
        chapter1page7 = (TextView) view.findViewById(R.id.chapter_1_page_7);
        chapter1page8 = (TextView) view.findViewById(R.id.chapter_1_page_8);
        chapter1page9 = (TextView) view.findViewById(R.id.chapter_1_page_9);
        chapter1page10 = (TextView) view.findViewById(R.id.chapter_1_page_10);

        chapter2page1 = (TextView) view.findViewById(R.id.chapter_2_page_1);
        chapter2page2 = (TextView) view.findViewById(R.id.chapter_2_page_2);
        chapter2page3 = (TextView) view.findViewById(R.id.chapter_2_page_3);
        chapter2page4 = (TextView) view.findViewById(R.id.chapter_2_page_4);
        chapter2page5 = (TextView) view.findViewById(R.id.chapter_2_page_5);
        chapter2page6 = (TextView) view.findViewById(R.id.chapter_2_page_6);
        chapter2page7 = (TextView) view.findViewById(R.id.chapter_2_page_7);
        chapter2page8 = (TextView) view.findViewById(R.id.chapter_2_page_8);
        chapter2page9 = (TextView) view.findViewById(R.id.chapter_2_page_9);
        chapter2page10 = (TextView) view.findViewById(R.id.chapter_2_page_10);

        chapter3page1 = (TextView) view.findViewById(R.id.chapter_3_page_1);
        chapter3page2 = (TextView) view.findViewById(R.id.chapter_3_page_2);
        chapter3page3 = (TextView) view.findViewById(R.id.chapter_3_page_3);
        chapter3page4 = (TextView) view.findViewById(R.id.chapter_3_page_4);
        chapter3page5 = (TextView) view.findViewById(R.id.chapter_3_page_5);
        chapter3page6 = (TextView) view.findViewById(R.id.chapter_3_page_6);
        chapter3page7 = (TextView) view.findViewById(R.id.chapter_3_page_7);
        chapter3page8 = (TextView) view.findViewById(R.id.chapter_3_page_8);
        chapter3page9 = (TextView) view.findViewById(R.id.chapter_3_page_9);
        chapter3page10 = (TextView) view.findViewById(R.id.chapter_3_page_10);

        chapter4page1 = (TextView) view.findViewById(R.id.chapter_4_page_1);
        chapter4page2 = (TextView) view.findViewById(R.id.chapter_4_page_2);
        chapter4page3 = (TextView) view.findViewById(R.id.chapter_4_page_3);
        chapter4page4 = (TextView) view.findViewById(R.id.chapter_4_page_4);
        chapter4page5 = (TextView) view.findViewById(R.id.chapter_4_page_5);
        chapter4page6 = (TextView) view.findViewById(R.id.chapter_4_page_6);
        chapter4page7 = (TextView) view.findViewById(R.id.chapter_4_page_7);
        chapter4page8 = (TextView) view.findViewById(R.id.chapter_4_page_8);
        chapter4page9 = (TextView) view.findViewById(R.id.chapter_4_page_9);
        chapter4page10 = (TextView) view.findViewById(R.id.chapter_4_page_10);

        chapter1page1.setText(R.string.chapter_1_page_1);
        chapter1page2.setText(R.string.chapter_1_page_2);
        chapter1page3.setText(R.string.chapter_1_page_3);
        chapter1page4.setText(R.string.chapter_1_page_4);
        chapter1page5.setText(R.string.chapter_1_page_5);
        chapter1page6.setText(R.string.chapter_1_page_6);
        chapter1page7.setText(R.string.chapter_1_page_7);
        chapter1page8.setText(R.string.chapter_1_page_8);
        chapter1page9.setText(R.string.chapter_1_page_9);
        chapter1page10.setText(R.string.chapter_1_page_10);

        chapter2page1.setText(R.string.chapter_2_page_1);
        chapter2page2.setText(R.string.chapter_2_page_2);
        chapter2page3.setText(R.string.chapter_2_page_3);
        chapter2page4.setText(R.string.chapter_2_page_4);
        chapter2page5.setText(R.string.chapter_2_page_5);
        chapter2page6.setText(R.string.chapter_2_page_6);
        chapter2page7.setText(R.string.chapter_2_page_7);
        chapter2page8.setText(R.string.chapter_2_page_8);
        chapter2page9.setText(R.string.chapter_2_page_9);
        chapter2page10.setText(R.string.chapter_2_page_10);

        chapter3page1.setText(R.string.chapter_3_page_1);
        chapter3page2.setText(R.string.chapter_3_page_2);
        chapter3page3.setText(R.string.chapter_3_page_3);
        chapter3page4.setText(R.string.chapter_3_page_4);
        chapter3page5.setText(R.string.chapter_3_page_5);
        chapter3page6.setText(R.string.chapter_3_page_6);
        chapter3page7.setText(R.string.chapter_3_page_7);
        chapter3page8.setText(R.string.chapter_3_page_8);
        chapter3page9.setText(R.string.chapter_3_page_9);
        chapter3page10.setText(R.string.chapter_3_page_10);

        chapter4page1.setText(R.string.chapter_4_page_1);
        chapter4page2.setText(R.string.chapter_4_page_2);
        chapter4page3.setText(R.string.chapter_4_page_3);
        chapter4page4.setText(R.string.chapter_4_page_4);
        chapter4page5.setText(R.string.chapter_4_page_5);
        chapter4page6.setText(R.string.chapter_4_page_6);
        chapter4page7.setText(R.string.chapter_4_page_7);
        chapter4page8.setText(R.string.chapter_4_page_8);
        chapter4page9.setText(R.string.chapter_4_page_9);
        chapter4page10.setText(R.string.chapter_4_page_10);

        chapter1list = new ArrayList<>();
        chapter2list = new ArrayList<>();
        chapter3list = new ArrayList<>();
        chapter4list = new ArrayList<>();

        chapter1list.add(chapter1page1);
        chapter1list.add(chapter1page2);
        chapter1list.add(chapter1page3);
        chapter1list.add(chapter1page4);
        chapter1list.add(chapter1page5);
        chapter1list.add(chapter1page6);
        chapter1list.add(chapter1page7);
        chapter1list.add(chapter1page8);
        chapter1list.add(chapter1page9);
        chapter1list.add(chapter1page10);

        chapter2list.add(chapter2page1);
        chapter2list.add(chapter2page2);
        chapter2list.add(chapter2page3);
        chapter2list.add(chapter2page4);
        chapter2list.add(chapter2page5);
        chapter2list.add(chapter2page6);
        chapter2list.add(chapter2page7);
        chapter2list.add(chapter2page8);
        chapter2list.add(chapter2page9);
        chapter2list.add(chapter2page10);

        chapter3list.add(chapter3page1);
        chapter3list.add(chapter3page2);
        chapter3list.add(chapter3page3);
        chapter3list.add(chapter3page4);
        chapter3list.add(chapter3page5);
        chapter3list.add(chapter3page6);
        chapter3list.add(chapter3page7);
        chapter3list.add(chapter3page8);
        chapter3list.add(chapter3page9);
        chapter3list.add(chapter3page10);

        chapter4list.add(chapter4page1);
        chapter4list.add(chapter4page2);
        chapter4list.add(chapter4page3);
        chapter4list.add(chapter4page4);
        chapter4list.add(chapter4page5);
        chapter4list.add(chapter4page6);
        chapter4list.add(chapter4page7);
        chapter4list.add(chapter4page8);
        chapter4list.add(chapter4page9);
        chapter4list.add(chapter4page10);

        for (int i = 0; i < chapter1list.size(); i++) {
            if (!MainActivity.diaryList.contains(0 + ", " + (i + 1) * 3)) {
                chapter1list.get(i).setVisibility(View.INVISIBLE);
            }
        }

        for (int i = 0; i < chapter2list.size(); i++) {
            if (!MainActivity.diaryList.contains(1 + ", " + (i + 1) * 3)) {
                chapter2list.get(i).setVisibility(View.INVISIBLE);
            }
        }

        for (int i = 0; i < chapter3list.size(); i++) {
            if (!MainActivity.diaryList.contains(2 + ", " + (i + 1) * 3)) {
                chapter3list.get(i).setVisibility(View.INVISIBLE);
            }
        }

        for (int i = 0; i < chapter4list.size(); i++) {
            if (!MainActivity.diaryList.contains(3 + ", " + (i + 1) * 3)) {
                chapter4list.get(i).setVisibility(View.INVISIBLE);
            }
        }

        if (storyBGM != null) {
            storyBGM.reset();
            storyBGM.release();
        }

        storyBGM = MediaPlayer.create(getContext(), R.raw.story);
        storyBGM.setLooping(true);
        storyBGM.setVolume(volumeMusic, volumeMusic);
        storyBGM.start();

        return view;
    }

}
