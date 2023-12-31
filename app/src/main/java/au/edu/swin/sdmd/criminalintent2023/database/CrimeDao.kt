package au.edu.swin.sdmd.criminalintent2023.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import au.edu.swin.sdmd.criminalintent2023.Crime
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * @version ch12
 */
@Dao
interface CrimeDao {
  @Query("SELECT * FROM crime")
  // this does not work!!:
  // suspend fun getCrimes(): List<Crime>
  fun getCrimes(): Flow<List<Crime>>

  @Query("SELECT * FROM crime WHERE id=(:id)")
  suspend fun getCrime(id: UUID): Crime

  //ch13: update
  @Update
  suspend fun updateCrime(crime: Crime)
}
