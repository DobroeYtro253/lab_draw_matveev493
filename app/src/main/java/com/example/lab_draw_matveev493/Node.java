package com.example.lab_draw_matveev493;

import android.graphics.Paint;
import android.icu.text.TimeZoneFormat;

public class Node {
    public float x, y;
    public int id;
    public String text;

    public Node(float x, float y, int id, String text)
    {
        this.x = x;
        this.y = y;
        this.id = id;
        this.text = text;
    }

}
