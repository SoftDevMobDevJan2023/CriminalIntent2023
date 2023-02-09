package au.edu.swin.sdmd.criminalintent2023

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.swin.sdmd.criminalintent2023.database.CrimeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

  /** Use StateFlow to maintain a single stream of data from your database
   and cache the results so they can quickly be displayed to the user.
    StateFlow always has a value that observers
    can collect from its stream. It starts with an initial value and caches the
    latest value that was emitted into the stream.
  */
  val _crimes : MutableStateFlow<List<Crime>> = MutableStateFlow(emptyList())
  val crimes: StateFlow<List<Crime>>
    get() = _crimes.asStateFlow()

  // 12.1: crimeRepository.getCrimes()

  init {
    // ch12: use database result caching
    viewModelScope.launch {
      crimeRepository.getCrimes().collect {
        _crimes.value = it
      }
    }
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
