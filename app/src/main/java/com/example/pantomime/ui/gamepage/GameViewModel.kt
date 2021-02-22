package com.example.pantomime.ui.gamepage

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//vibrates patterns
private val CORRECT_BUZE_PATTERN = longArrayOf(100,100,100,100,100,100)
private val WRONG_BUZE_PATTERN = longArrayOf(500,100,500)
private val GAME_OVER_PATTERN = longArrayOf(0,2000)
private val NO_BUZE_PATTERN = longArrayOf(0)

enum class BuzzType(val pattern : LongArray){
    CORRECT(CORRECT_BUZE_PATTERN),
    GAME_OVER(GAME_OVER_PATTERN),
    WRONG(WRONG_BUZE_PATTERN),
    NO_BUZZ(NO_BUZE_PATTERN)
}

class GameViewModel : ViewModel() {

    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz : LiveData<BuzzType>
        get() = _eventBuzz

    lateinit var wordList: MutableList<String>

    var timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private val _word = MutableLiveData("")
    val word: LiveData<String>
        get() = _word

    private val _eventGameFinish = MutableLiveData(false)
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    var times = 6

    init {
        getWordList()
        nextWord()

        _eventBuzz.value = BuzzType.NO_BUZZ

        timer = object : CountDownTimer(600000L, 1000L) {
            override fun onTick(time: Long) {
                _currentTime.value = time /1000
            }

            override fun onFinish() {
                _eventGameFinish.value = true
                _currentTime.value = 0L
                _eventBuzz.value = BuzzType.GAME_OVER
            }
        }

        timer.start()
    }

    fun onCorrect() {
        _score.value = _score.value?.plus(1)
        times--
        nextWord()
        _eventBuzz.value = BuzzType.CORRECT

    }

    fun onWrong() {
        _score.value = _score.value?.minus(1)
        times--
        nextWord()
        _eventBuzz.value = BuzzType.WRONG
    }

    fun nextWord() {
        if (times == 0) {
            _eventGameFinish.value = true
            _eventBuzz.value = BuzzType.GAME_OVER
        }
        _word.value = wordList.removeAt(0)
    }

    fun getWordList() {
        wordList = mutableListOf(
            "پانتومیم",
            "سود",
            "فرابنفش",
            "صنف",
            "صحن",
            "رادیولوژی",
            "خرناس",
            "حنابندان",
            "ایزوگام",
            "کارآفرین",
            "دولت",
            "وجدان",
            "جامدادی",
            "امامزاده",
            "جدول",
            "هردنبیل",
            "اندرونی",
            "انفورماتیک",
            "مانیکور",
            "ازگیل",
            "داعش",
            "پاپیروس",
            "ایزوگام",
            "لاتاری",
            "اسکله",
            "شناسنامه",
            "سیمرغ",
            "داروخانه",
            "سیانور",
            "کارنامه",
            "دورنما",
            "تارنما",
            "اختاپوس",
            "کروموزوم",
            "محلول",
            "پهباد",
            "فروگذار",
            "دومینو",
            "تشریفات",
            "یاتاقان",
            "ابتکار",
            "سندروم",
            "اندروید",
            "انتگرال",
            "سرطان مزمن",
            " طغیان رود",
            "شنل عروس",
            "گل قالی",
            " شلوارک نخی",
            " نخل خرما",
            " کشمش پلویی",
            " ملیله دوزی",
            "فلج مادرزاد",
            "دانشگاه ماساچوست",
            " انبر نسارا",
            "مقررات ملی",
            "شیر هموژنیزه",
            "فئودالیسم کاتولیک",
            " خرچنگ ترسو",
            " مرغ عشق",
            "ساعت مچی",
            "ارباب رجوع",
            "کوکو سبزی",
            "قفسه کتاب",
            "کباب تابه ای",
            "کویر لوت",
            "خط استوا",
            "قهوه ترک",
            "قطب جنوب",
            "آلوچه ترش",
            "جنگ نرم",
            "اهرام ثلاثه",
            "سوپاپ اطمینان",
            "غذای حضرتی",
            "اضافه حقوق",
            "آیت الله دستغیب",
            "حوزه استحفاظی",
            "دارالمجانین",
            "نازک نارنجی",
            "سفرنامه مارکوپلو",
            "پروتکل بهداشتی",
            "پوریا ولی",
            "همت مضاعف"
        ).shuffled() as MutableList<String>
    }


    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    val message = MutableLiveData("")

    fun getData(){
        viewModelScope.launch (Dispatchers.IO) {
            message.postValue(getServerData())
        }
    }

    suspend fun getServerData() : String {
        delay(5000)
        return "مارو در بازار حمایت کنید"
    }
}