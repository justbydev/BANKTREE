package com.aram.banktree;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageTotalbook {
    private static ManageTotalbook manageTotalbook=null;
    ArrayList<Totalbook> Totalbook_total;
    Totalbook totalbook;

    public static ManageTotalbook getInstance(){
        if(manageTotalbook==null){
            manageTotalbook=new ManageTotalbook();
        }
        return manageTotalbook;
    }

    private ManageTotalbook(){
        totalbook=new Totalbook();
        Totalbook_total=new ArrayList<>();
    }

    public void getTotalbook(){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Content");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String json=snapshot.getValue().toString();
                    json=json.replaceAll(" ", "§");
                    json=json.replaceAll("\n", "`");
                    try {
                        System.out.println(json);
                        JSONObject jsonObject=new JSONObject(json);
                        String writertmp=jsonObject.optString("writer", null);
                        if(writertmp==null){
                            writertmp=jsonObject.getString("§writer");
                        }
                        writertmp=writertmp.replaceAll("§", " ");
                        String writer=writertmp.replaceAll("`", "\n");
                        totalbook.setWriter(writer);
                        String titletmp=jsonObject.optString("title", null);
                        if(titletmp==null){
                            titletmp=jsonObject.getString("§title");
                        }
                        titletmp=titletmp.replaceAll("§", " ");
                        String title=titletmp.replaceAll("`", "\n");
                        totalbook.setTitle(title);
                        String page=jsonObject.optString("page", null);
                        if(page==null){
                            page=jsonObject.getString("§page");
                        }
                        totalbook.setPage(page);
                        JSONArray color=jsonObject.optJSONArray("color");
                        if(color==null){
                            color=jsonObject.getJSONArray("§color");
                        }
                        System.out.println(writer);
                        System.out.println(title);
                        System.out.println("=====================================");
                        System.out.println("++++++++++++++++++++++++++++++++++++");
                        ArrayList<String> c=new ArrayList<>();
                        for(int i=0; i<color.length(); i++){
                            String tmp=color.get(i).toString();
                            tmp=tmp.replaceAll("§", " ");
                            String colortmp=tmp.replaceAll("`", "\n");
                            c.add(colortmp);
                        }
                        totalbook.setColor(c);
                        JSONArray content=jsonObject.optJSONArray("content");
                        if(content==null){
                            content=jsonObject.getJSONArray("§content");
                        }
                        ArrayList<String> con=new ArrayList<>();
                        for(int i=0; i<content.length(); i++){
                            String str=content.get(i).toString();
                            str=str.replaceAll("§", " ");
                            String tempstr=str.replaceAll("`", "\n");
                            con.add(tempstr);
                        }
                        totalbook.setContent(con);
                        Totalbook_total.add(totalbook);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    totalbook=new Totalbook();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Totalbook> getTotalbook_total() {
        return Totalbook_total;
    }

    public ArrayList<Totalbook> gettwenty(){
        int cnt=0;
        ArrayList<Totalbook> temp=new ArrayList<>();
        for(int i=Totalbook_total.size()-1; i>=0; i--){
            temp.add(Totalbook_total.get(i));
            cnt++;
            if (cnt >= 20) {
                break;
            }
        }
        return temp;
    }
    public void setTotalbook_total(ArrayList<Totalbook> totalbook_total) {
        Totalbook_total = totalbook_total;
    }
}
