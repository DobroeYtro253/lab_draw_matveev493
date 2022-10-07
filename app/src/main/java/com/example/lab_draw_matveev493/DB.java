package com.example.lab_draw_matveev493;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.nio.channels.InterruptedByTimeoutException;

public class DB extends SQLiteOpenHelper {

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Graph (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";
        db.execSQL(sql);
        sql = "CREATE TABLE Node (id INT ,idGraph INT, x FLOAT, y FLOAT, txt TEXT);";
        db.execSQL(sql);
        sql = "CREATE TABLE Link (id INT ,idGraph INT, a INT, b INT);";
        db.execSQL(sql);
    }

    public void addGraph(String name)
    {
        String sql = "INSERT INTO Graph (name) VALUES('" + name + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public String selectGraph(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT id FROM Graph WHERE name = '" + name + "';";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst() == true) return cur.getString(0);
        return "";
    }
    public void deleteGraph(int id)
    {
        String sql = "DELETE FROM Graph WHERE id = '" + id + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }


    public void addNode(int id, float x, float y, int idGraph, String txt)
    {
        String sql = "INSERT INTO Node VALUES('" + id + "', '" + idGraph + "', '" + x + "', '" + y + "', '" + txt + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public void deleteNode(int idGraph)
    {
        String sql = "DELETE FROM Node WHERE idGraph = '" + idGraph + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public String selectNodeX(int idGraph, int i)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT x FROM Node WHERE idGraph = '" + idGraph + "' and id = '" + i + "';";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst() == true) return cur.getString(0);
        return "0.0f";
    }
    public String selectNodeText(int idGraph, int i)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT txt FROM Node WHERE idGraph = '" + idGraph + "' and id = '" + i + "';";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst() == true) return cur.getString(0);
        return "0.0f";
    }
    public String selectNodeY(int idGraph, int i)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT y FROM Node WHERE idGraph = '" + idGraph + "' and id = '" + i + "';";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst() == true) return cur.getString(0);
        return "0.0f";
    }
    public String selectNodeCount(int idGraph)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT COUNT (*) FROM Node WHERE idGraph = '" + idGraph + "';";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst() == true) return cur.getString(0);
        return "0.0f";
    }

    public void addLink(int id, int a, int b, int idGraph)
    {
        String sql = "INSERT INTO Link VALUES('" + id + "', '" + idGraph + "', '" + a + "', '" + b + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public void deleteLink(int idGraph)
    {
        String sql = "DELETE FROM Link WHERE idGraph = '" + idGraph + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public String selectLinkCount(int idGraph)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT COUNT (*) FROM Link WHERE idGraph = '" + idGraph + "';";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst() == true) return cur.getString(0);
        return "0.0f";
    }
    public Integer selectLinkA(int idGraph, int i)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT a FROM Link WHERE idGraph = '" + idGraph + "' and id = '" + i + "';";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst() == true) return cur.getInt(0);
        return 0;
    }
    public Integer selectLinkB(int idGraph, int i)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT b FROM Link WHERE idGraph = '" + idGraph + "' and id = '" + i + "';";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst() == true) return cur.getInt(0);
        return 0;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
