package com.example.lab_draw_matveev493;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    ListView lv;
    String[] graph;
    String name = "";
    String idGraph = "";
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        refresh();

        tvName = findViewById(R.id.textViewName);
        tvName.setText("Name: " + name);
    }
    public void refresh()
    {
        graph = new String[Graph.node.size() + Graph.link.size()];
        lv = findViewById(R.id.listGraph);
        for ( int i = 0; i < Graph.node.size(); i++)
        {
            Node n = Graph.node.get(i);
            String nx =  Float.toString(n.x);
            String ny =  Float.toString(n.y);
            String nid = Integer.toString(n.id);
            String ntxt = n.text;
            graph[i] = "Type: Node, id: " + nid + ", X: " + nx + ", Y: " + ny + ", Text: " + ntxt;
        }
        for ( int i = 0; i < Graph.link.size(); i++)
        {
            Link l = Graph.link.get(i);
            String la =  Integer.toString(l.a);
            String lb =  Integer.toString(l.b);
            String lid = Integer.toString(l.id);
            graph[i + Graph.node.size()] = "Type: Link, id: " + lid + ", A: " + la + ", B: " + lb;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, graph);
        lv.setAdapter(adapter);
    }
    public void on_create(View v)
    {
        if (name != "")
        {
            idGraph = Graph.g.selectGraph(name);
            if(idGraph == "")
            {
                Graph.g.addGraph(name);
                idGraph = Graph.g.selectGraph(name);
                for ( int i = 0; i < Graph.node.size(); i++)
                {
                    Node n = Graph.node.get(i);
                    Graph.g.addNode(n.id, n.x, n.y, Integer.parseInt(idGraph), n.text);
                }
                for ( int i = 0; i < Graph.link.size(); i++)
                {
                    Link n = Graph.link.get(i);
                    Graph.g.addLink(n.id, n.a, n.b, Integer.parseInt(idGraph));
                }
            }
            else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Graph already exists", Toast.LENGTH_SHORT);
                    toast.show();
                }

        }
        else
            {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Enter name graph", Toast.LENGTH_SHORT);
                toast.show();
            }
    }
    public void on_save(View v)
    {
        if (name != "")
        {
            idGraph = Graph.g.selectGraph(name);
            if(idGraph != "")
            {
                Graph.g.deleteNode(Integer.parseInt(idGraph));
                Graph.g.deleteLink(Integer.parseInt(idGraph));
                for ( int i = 0; i < Graph.node.size(); i++)
                {
                    Node n = Graph.node.get(i);
                    Graph.g.addNode(n.id, n.x, n.y, Integer.parseInt(idGraph), n.text);
                }
                for ( int i = 0; i < Graph.link.size(); i++)
                {
                    Link n = Graph.link.get(i);
                    Graph.g.addLink(n.id, n.a, n.b, Integer.parseInt(idGraph));
                }
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Graph does not exist", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Enter name graph", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void on_delete(View v)
    {
        if (name != "")
        {
            idGraph = Graph.g.selectGraph(name);
            if(idGraph != "")
            {
                Graph.g.deleteNode(Integer.parseInt(idGraph));
                Graph.g.deleteLink(Integer.parseInt(idGraph));
                Graph.g.deleteGraph(Integer.parseInt(idGraph));
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Graph does not exist", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Enter name graph", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void on_load(View v)
    {
        if (name != "")
        {
            idGraph = Graph.g.selectGraph(name);
            if(idGraph != "")
            {
                Graph.node.clear();
                Graph.link.clear();
                for ( int i = 0; i < Integer.valueOf(Graph.g.selectNodeCount(Integer.parseInt(idGraph))); i++)
                {
                    try
                    {
                        MainActivity.gv.add_node(Float.valueOf(Graph.g.selectNodeX(Integer.parseInt(idGraph), i)), Float.valueOf(Graph.g.selectNodeY(Integer.parseInt(idGraph), i)), Graph.g.selectNodeText(Integer.parseInt(idGraph), i));
                    }
                    catch (Exception e){return;}
                }
                for ( int i = 0; i < Integer.valueOf(Graph.g.selectLinkCount(Integer.parseInt(idGraph))); i++)
                {
                    try
                    {
                        MainActivity.gv.link_nodeAB(Integer.valueOf(Graph.g.selectLinkA(Integer.parseInt(idGraph), i)), Integer.valueOf(Graph.g.selectLinkB(Integer.parseInt(idGraph), i)));
                    }
                    catch (Exception e){return;}

                }
                refresh();
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Graph does not exist", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Enter name graph", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void on_rename(View v)
    {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Rename");
        alert.setMessage("Enter name graph");
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                name = String.valueOf(input.getText());
                tvName.setText("Name: " + name);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();

    }
    public void on_clone(View v)
    {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Clone");
        alert.setMessage("Enter id graph");
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    String id = String.valueOf(input.getText());
                    String txt = Graph.node.get(Integer.parseInt(id)).text;
                    MainActivity.gv.add_node(100.0f, 100.f, txt);
                    refresh();
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Node does not exist", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();

    }
    public void on_Back(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slidein, R.anim.slideout);
    }
}