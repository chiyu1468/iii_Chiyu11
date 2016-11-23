package tw.org.iii.iii_chiyu11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 606lab on 2016/11/21.
 */

public class ChiyuDBHelper extends SQLiteOpenHelper {

    // SQL指令
    private final String createTable =
            "CREATE TABLE custom (id INTEGER PRIMARY KEY,cname TEXT,brith DATE,tel TEXT)";

    // SQL指令_END



    public ChiyuDBHelper(
            Context context, String name,
            SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(createTable);
        Log.v("chiyu","onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.v("chiyu","onUpgrade");
    }
}
