package au.edu.swin.sdmd.criminalintent2023

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.sdmd.criminalintent2023.databinding.ListItemCrimeBinding

/**
 * The RecyclerView expects an item view to be wrapped in an instance of ViewHolder.
 * A ViewHolder stores a reference to an item’s view.
 * But, as usual, you are not going to interact directly with the View.
 * You are going to use View Binding
 *
 * @version: Chapter 10
 */
class CrimeHolder(
  private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
  /*
   * cache the crime being bound into a property and
   * set the text values on crimeTitle and crimeDate
   * */
  fun bind(crime: Crime) {
    binding.crimeTitle.text = crime.title
    binding.crimeDate.text = crime.date.toString()

    /*
     * set an OnClickListener to respond to user's click on an item.
     * Because you want the entire row to be clickable,
     * set the OnClickListener on the root property of the binding.
     * */
    binding.root.setOnClickListener {
      Toast.makeText(
        binding.root.context,
        "${crime.title} clicked!",
        Toast.LENGTH_SHORT
      ).show()
    }
  }
}

/**
 * RecyclerView does not create ViewHolders itself. Instead, it asks an adapter.
 * An adapter is a controller object that sits between the RecyclerView and the data set that the
 * RecyclerView should display.
 *
 * The adapter is responsible for:
 * - creating the necessary ViewHolders when asked
 * - binding data to ViewHolders from the model layer when asked
 *
 * The recycler view is responsible for:
 *  - asking the adapter to create a new ViewHolder
 *  - asking the adapter to bind a ViewHolder to the item from the backing data at a given position
 */
class CrimeListAdapter(
  private val crimes: List<Crime>
) : RecyclerView.Adapter<CrimeHolder>() {

  /**
   * create a binding to display, wrapping the view in a view holder, and returning the
  result. Inflate and bind a ListItemCrimeBinding and
  pass the resulting binding to a new instance of CrimeHolder
   * */
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) : CrimeHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
    return CrimeHolder(binding)
  }

  /**
   *  Populate a given holder with the crime from a given position. In this case, you get
  the crime from the crime list at the requested position. You then use the title
  and date from that crime to set the text in the corresponding text views.
   **/
  override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
    val crime = crimes[position]
    holder.bind(crime)
  }
  override fun getItemCount() = crimes.size
}
