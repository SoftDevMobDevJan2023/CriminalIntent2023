package au.edu.swin.sdmd.criminalintent2023.database

import androidx.room.TypeConverter
import java.util.*

/**
 * @version ch12
 */
class CrimeTypeConverters {
  @TypeConverter
  fun fromDate(date: Date): Long {
    return date.time
  }
  @TypeConverter
  fun toDate(millisSinceEpoch: Long): Date {
    return Date(millisSinceEpoch)
  }
}
