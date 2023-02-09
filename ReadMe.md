App: Criminal Intent
======================
***Reference***: BNR book, Chapter 9

***CriminalIntent*** records the details of "office crimes" – things like leaving dirty dishes in the break room sink or walking away
from an empty shared printer after documents have printed.

With CriminalIntent, you can make a record of a crime including a title, a date, and a photo. You
can also identify a suspect from your contacts and lodge a complaint via email, Twitter, Facebook,
or another app. After documenting and reporting a crime, you can proceed with your work free of
resentment and ready to focus on the business at hand.

CriminalIntent is a complex app that will take 11 chapters to complete. It will have a list-detail
interface: The main screen will display a list of recorded crimes, and users will be able to add new
crimes or select an existing crime to view and edit its details (Figure 8.1)

# Chapter 12

***Room*** (database access API):
- Version 2.4.2 mentioned in BNR book does not work properly (Error with "suspend" use for `CrimeDAO.getCrimes`).
  - Change to version "2.4.3" solved this

***CrimeDAO.getCrimes()***:
```
interface CrimeDao {
  @Query("SELECT * FROM crime")
  // this does not work!!:
  // suspend fun getCrimes(): List<Crime>
  fun getCrimes(): Flow<List<Crime>>

  @Query("SELECT * FROM crime WHERE id=(:id)")
  suspend fun getCrime(id: UUID): Crime
}
```