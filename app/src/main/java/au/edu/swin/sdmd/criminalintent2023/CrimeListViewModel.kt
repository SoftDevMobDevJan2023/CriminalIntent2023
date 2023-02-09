package au.edu.swin.sdmd.criminalintent2023

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.swin.sdmd.criminalintent2023.database.CrimeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

/**
 * @version
 * - chapter 10 (created) <br>
 * - chapter 12: coroutine
 * Coroutines provide a high-level and safer set of tools to help you build asynchronous code.
 * Under the hood, Kotlin’s coroutines use threads to perform work in parallel,
 * but you often do not have to worry about this detail.
 * Coroutines make it easy to start work on the main thread, hop over to a background thread to
 * perform asynchronous work, and then return the result back to the main thread
 *
 */
// ch12
private const val TAG = "CrimeListViewModel"

class CrimeListViewModel : ViewModel() {
  // ch12
  private val crimeRepository = CrimeRepository.get()

  val crimes = //mutableListOf<Crime>()
    crimeRepository.getCrimes()

  init {
    // ch12: use coroutine
//    Log.d(TAG, "init starting")
//    viewModelScope.launch {
//      Log.d(TAG, "coroutine launched")
//      crimes += loadCrimes()
//      Log.d(TAG, "Loading crimes finished")
//    }
  }

  /**
   * place crime loading in a suspending function so that it can be
   * invoked within a thread (coroutine) (see above)
   */
//  suspend fun loadCrimes() : List<Crime> {
    /* ch12: load crimes from database
    val result = mutableListOf<Crime>()
    //  delay is running inside a coroutine,
    //  your UI is still capable of drawing any
    // new updates and can instantly respond to user input
    delay(1500)
    for (i in 0 until 100) {
      val crime = Crime(
        id = UUID.randomUUID(),
        title = "Crime #$i",
        date = Date(),
        isSolved = i % 2 == 0
      )
      result += crime
    }

    return result*/
//    return crimeRepository.getCrimes()
//  }
}
