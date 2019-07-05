package android.forecast.common.helper

data class ScreenUiData<T>(val state: State = State.IDLE, val data: T, val error: String? = null)

enum class State {

    /**
     * State loading stands for the loading when the screen is beginning
     */
    LOADING,
    /**
     * State refreshing stands for the loading when the screen has already displayed data but there is a refresh in progress
     */
    REFRESHING,
    /**
     * State idle stands for the screen is not being doing anything
     */
    IDLE,
    /**
     * State error stands for the screen is in error
     */
    ERROR,
    /**
     * State empty stands for the screen has no data to display
     */
    EMPTY,
    /**
     * State success is for inform that the main purpose of the screen is finished. It Should be the last state of a ScreenUiData.
     * Example: form creation of some data, when the creation is done SUCCESS shoud be trigger.
     */
    SUCCESS
}