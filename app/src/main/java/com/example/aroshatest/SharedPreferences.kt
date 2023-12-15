import android.content.Context
import android.content.SharedPreferences
import com.example.aroshatest.UserInfo

object UserPreferences {
    private const val PREF_NAME = "UserPrefs"
    private const val KEY_FIRST_NAME = "firstName"
    private const val KEY_LAST_NAME = "lastName"
    private const val KEY_ID = "id"
    private const val KEY_BIRTHDATE = "birthdate"

    fun saveUser(context: Context, userInfo: UserInfo) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.putString(KEY_FIRST_NAME, userInfo.firstName)
        editor.putString(KEY_LAST_NAME, userInfo.lastName)
        editor.putString(KEY_ID, userInfo.id)
        editor.putString(KEY_BIRTHDATE, userInfo.birthdate)

        editor.apply()
    }

    fun getUser(context: Context): UserInfo? {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val firstName = prefs.getString(KEY_FIRST_NAME, null)
        val lastName = prefs.getString(KEY_LAST_NAME, null)
        val id = prefs.getString(KEY_ID, null)
        val birthdate = prefs.getString(KEY_BIRTHDATE, null)

        return if (firstName != null && lastName != null && id != null && birthdate != null) {
            UserInfo(firstName, lastName, id, birthdate)
        } else {
            null
        }
    }
}
