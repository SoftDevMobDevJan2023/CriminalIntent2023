package au.edu.swin.sdmd.criminalintent2023

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

/**
 * @version chapter 10
 */
private const val TAG = "CrimeListViewModel"

class CrimeListViewModel : ViewModel() {
  val crimes = mutableListOf<Crime>()
  init {
    // ch12: 1st coroutine
    Log.d(TAG, "init starting")
    viewModelScope.launch {
      // delaying work
      Log.d(TAG, "coroutine launched")
      crimes += loadCrimes()
      Log.d(TAG, "Loading crimes finished")
    }
  }

  suspend fun loadCrimes(): List<Crime> {
    val result = mutableListOf<Crime>()
    for (i in 0 until 100) {
      val crime = Crime(
        id = UUID.randomUUID(),
        title = "Crime #$i",
        date = Date(),
        isSolved = i % 2 == 0
      )
      result += crime
    }

    return result
  }
}
