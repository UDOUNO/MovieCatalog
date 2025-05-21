import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.MD.data.datasource.local.LocalDataSourceDao
import com.example.MD.data.entity.DB.GenreForDBModelDB

@Database(
    version = 1,
    entities = [GenreForDBModelDB::class]
)
abstract class AppDataBase: RoomDatabase(){
    abstract fun getDao():LocalDataSourceDao
}