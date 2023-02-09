package au.edu.swin.sdmd.criminalintent2023.database

import android.app.Application

/**
 * @version ch12
 */
class CriminalIntentApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    CrimeRepository.initialize(this)
  }
}
