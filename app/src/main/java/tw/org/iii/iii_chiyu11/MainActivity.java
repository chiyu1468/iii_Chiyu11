package tw.org.iii.iii_chiyu11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private ChiyuDBHelper myHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        String dataBaseName = "iii";
        int ver = 2; // 版本號 比較新 會執行 onUpgrade
        myHelper = new ChiyuDBHelper(this,dataBaseName,null,ver);

        db = myHelper.getReadableDatabase();

    }

    // SQL 的增刪修查

    public void SQLQuery(View v){
        tv.setText("");
        // cmd = "SELECT * FROM custom";
        // db.execSQL(cmd);
        // 上面寫法 容易被 隱碼攻擊 所以這用這樣寫
        Cursor c1 = db.query("custom",null,null,null,null,null,null);

        while (c1.moveToNext()){
            // 誰曉得 c1.getString(0) 是啥 還要慢慢看 很煩
            String id = c1.getString(0);
            // 用 getColumnIndex 直接抓 “那一個”
            String cname = c1.getString(c1.getColumnIndex("cname"));
            String brith = c1.getString(c1.getColumnIndex("brith"));
            String tel = c1.getString(c1.getColumnIndex("tel"));
            tv.append("id: " + id + "\n cname: " + cname + "\n brithday: " + brith + "\n tel: " + tel + "\n=====\n");
        }

    }

    public void SQLAdd(View v){
        // INSERT INTO custom (cname,birth,tel) VALUES ('Chiyu','0001-01-01','0912345678')

        ContentValues cData = new ContentValues();
        cData.put("cname","Chiyu");
        cData.put("brith","0001-01-01");
        cData.put("tel","0912345678");

        db.insert("custom",null,cData);

        SQLQuery(null);
    }

    public void SQLDelete(View v){
        // DELETE FROM custom WHERE id = 2 AND cname = 'Chiyu'

        // 這種寫法 可以被隱碼攻擊
        db.delete("custom","id = 1 AND cname = 'Chiyu'",null);
        // 所以換成這麼奇怪的寫法
        db.delete("custom","id = ? AND cname = ?",new String[]{"3","Chiyu"});

        SQLQuery(null);
    }

    public void SQLEdit(View v){
        ContentValues cData = new ContentValues();
        cData.put("cname","Picard");
        cData.put("brith","0001-04-28");
        cData.put("tel","0987654321");
        db.update("custom",cData,"id = ?",new String[]{"8"});

        SQLQuery(null);
    }

//    public void SQLreset(View v){
//        db.execSQL("DROP TABLE custom");
//    }









}
