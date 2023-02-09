package au.edu.swin.sdmd.criminalintent2023.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import au.edu.swin.sdmd.criminalintent2023.Crime

/**
 * @version ch12
 */
@Database(entities = [ Crime::class ], version=1
  // disable the export and the associated Build's warning:
  // "Schema export directory is not provided..."
  , exportSchema = false
)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase : RoomDatabase() {
  abstract fun crimeDao(): CrimeDao

}