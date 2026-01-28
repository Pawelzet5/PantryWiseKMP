package org.example.pantrywisecmp.product.data

import org.example.pantrywisecmp.product.domain.*
import kotlin.time.Clock.System.now
import kotlin.time.Duration.Companion.days

object MockDataHelper {
    private val nowPlusDays = { days: Long ->
        (now() + days.days).toEpochMilliseconds()
    }

    fun getMockProductList() = listOf(
        Product(
            id = 1,
            name = "Milk",
            details = "3.2%",
            quantity = 2.0,
            productUnit = ProductUnit.LITER,
            category = ProductCategory.BEVERAGES,
            expirationDate = nowPlusDays(3)
        ),
        Product(
            id = 2,
            name = "Bread",
            details = "",
            quantity = 1.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.BAKERY,
            expirationDate = nowPlusDays(3)
        ),
        Product(
            id = 3,
            name = "Burger Buns",
            details = "",
            quantity = 4.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.BAKERY,
            expirationDate = nowPlusDays(1)
        ),
        Product(
            id = 4,
            name = "Grahamki",
            details = "",
            quantity = 5.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.BAKERY,
            expirationDate = nowPlusDays(2)
        ),
        Product(
            id = 6,
            name = "Apples",
            details = "",
            quantity = 6.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.PRODUCE,
            expirationDate = nowPlusDays(14)
        ),
        Product(
            id = 7,
            name = "Chicken Breast",
            details = "",
            quantity = 500.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.MEAT_SEAFOOD,
            expirationDate = nowPlusDays(3)
        ),
        Product(
            id = 8,
            name = "Rice",
            details = "",
            quantity = 1.0,
            productUnit = ProductUnit.KILOGRAM,
            category = ProductCategory.GRAINS_CEREALS
            // bez expirationDate = null domyślnie
        ),
        Product(
            id = 9,
            name = "Olive Oil",
            details = "",
            quantity = 250.0,
            productUnit = ProductUnit.MILLILITER,
            category = ProductCategory.OILS_FATS
        ),
        Product(
            id = 10,
            name = "Salt",
            details = "",
            quantity = 100.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.SPICES_SEASONINGS
        ),
        Product(
            id = 11,
            name = "Eggs",
            details = "",
            quantity = 12.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(30)
        ),
        Product(
            id = 12,
            name = "Cheese",
            details = "",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(8)
        ),
        Product(
            id = 13,
            name = "Mozzarella",
            details = "Fior di Latte",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(3)
        ),
        Product(
            id = 14,
            name = "Mozzarella",
            details = "Lidlowa",
            quantity = 2.0,
            productUnit = ProductUnit.BAG,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(3)
        ),
        Product(
            id = 15,
            name = "Cheddar",
            details = "",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(14)
        ),
        Product(
            id = 16,
            name = "Gouda",
            details = "",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(14)
        ),
        Product(
            id = 17,
            name = "Monterey Jack",
            details = "",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(-8)
        ),
        Product(
            id = 18,
            name = "Burrata",
            details = "",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(2)
        ),
        Product(
            id = 19,
            name = "Tomatoes",
            details = "Włoskie Mutti",
            quantity = 4.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.PRODUCE,
            expirationDate = nowPlusDays(6)
        )
    )

    fun getSuggestionsList() = listOf(
        "marchew", "ziemniaki", "pomidory", "ogórki świeże", "ogórki kiszone", "papryka czerwona",
        "papryka żółta", "papryka zielona", "cebula czerwona", "cebula biała", "czosnek świeży",
        "czosnek granulowany", "sałata lodowa", "sałata masłowa", "sałata rzymska", "brokuły",
        "kalafior", "cukinia", "bakłażan", "szpinak świeży", "jarmuż", "por", "seler naciowy",
        "seler korzeniowy", "rzodkiewka", "buraki surowe", "fasola szparagowa zielona",
        "fasola szparagowa żółta", "groszek zielony", "kapusta biała", "kapusta czerwona",
        "kalarepa", "pietruszka korzeń", "pietruszka natka", "koperek świeży", "rukola",
        "roszponka", "pęczek szczypioru", "pęczek koperku", "pieczarki", "boczniaki",
        "shiitake", "żabie udka", "pstrąg świeży", "łosoś świeży", "łosoś wędzony",
        "dorsz filet", "mintaj filet", "makrela świeża", "makrela wędzona", "śledź surowy",
        "tuńczyk w puszce w sosie własnym", "tuńczyk w puszce w oleju", "sardynki w oleju",
        "krewetki mrożone", "małże mrożone", "mule", "kalmar rings", "jabłka złote",
        "jabłka czerwone", "banany", "pomarańcze", "mandarynki", "cytryny", "limonki",
        "grapefruity", "kiwi zielone", "kiwi żółte", "gruszki konferencja", "brzoskwinie",
        "nektarynki", "śliwki węgierki", "morele", "wiśnie", "czereśnie", "truskawki",
        "maliny świeże", "jeżyny", "borówki amerykańskie", "porzeczki czarne", "porzeczki czerwone",
        "agrest", "migdały całe", "orzechy włoskie", "orzechy laskowe", "orzechy nerkowca",
        "pistacje", "orzechy brazylijskie", "pestki dyni prażone", "pestki słonecznika",
        "siemię lniane", "chia nasiona", "sok pomarańczowy 100%", "sok jabłkowy",
        "sok grejpfrutowy", "woda mineralna gazowana", "woda mineralna niegazowana",
        "mleko 2%", "mleko 3.2%", "mleko roślinne owsiane", "mleko migdałowe", "mleko sojowe",
        "jogurt naturalny 2%", "jogurt grecki 10%", "kefir 2%", "maślanka", "śmietana 18%",
        "śmietana 36%", "twaróg chudy", "twaróg półtłusty", "twaróg tłusty", "ser mozzarella",
        "ser feta", "ser gouda", "ser cheddar", "ser parmezan starty", "ser pleśniowy",
        "ser camembert", "ser brie", "masło ekstra", "margaryna do smarowania",
        "pierś z kurczaka filet", "udo z kurczaka", "cały kurczak", "filet z indyka",
        "schab wieprzowy", "karkówka wieprzowa", "żeberka wieprzowe", "polędwica wołowa",
        "mielone wołowe", "wołowina gulaszowa", "szynka gotowana", "szynka parzona",
        "boczek wędzony", "kiełbasa śląska", "kiełbasa biała surowa", "parówki drobiowe",
        "parówki wieprzowe", "chleb pszenny", "chleb żytni", "chleb pełnoziarnisty",
        "bułki pszenne", "bułki kajzerki", "bułki grahamki", "bagietka", "chleb tostowy",
        "pitki pszenne", "tortille pszenne", "ryż biały długoziarnisty", "ryż brązowy",
        "ryż basmati", "ryż jaśminowy", "makaron spaghetti", "makaron penne", "makaron fusilli",
        "makaron tagliatelle", "makaron pełnoziarnisty", "kasza gryczana prażona", "kasza jaglana",
        "kasza pęczak", "kasza manna", "płatki owsiane górskie", "płatki owsiane błyskawiczne",
        "płatki jaglane", "płatki quinoa", "soczewica czerwona", "soczewica zielona",
        "ciecierzyca sucha", "fasola biała sucha", "fasola czerwona", "fasola czarna",
        "groch łuskany", "pomidory krojone w puszce", "pomidory całych w puszce",
        "koncentrat pomidorowy", "kukurydza konserwowa", "groszek konserwowy", "papryka konserwowa",
        "ogórki kiszone", "ogórki konserwowe", "buraki w occie", "dżem truskawkowy",
        "dżem malinowy", "dżem wiśniowy", "miód wielokwiatowy", "powidła śliwkowe",
        "mus jabłkowy", "masło orzechowe gładkie", "sól kuchenna", "pieprz czarny mielony",
        "pieprz biały mielony", "papryka słodka mielona", "papryka ostra mielona",
        "chili mielone", "kurkuma mielona", "kmin rzymski", "kminek mielony",
        "kolendra mielona", "bazylia suszona", "oregano suszone", "tymianek suszony",
        "majeranek suszony", "czosnek granulowany", "cebula suszona", "zioła prowansalskie",
        "przyprawa do kurczaka", "przyprawa gyros", "curry madras", "garam masala",
        "oliwa z oliwek extra vergine", "olej rzepakowy rafinowany", "olej lniany tłoczony na zimno",
        "ocet balsamiczny", "ocet jabłkowy", "musztarda sarepska", "ketchup Heinz",
        "majonez hellmanns", "czosnek niedźwiedzi suszony", "imbir suszony", "cukier biały kryształ",
        "cukier trzcinowy brązowy", "mąka pszenna tortowa", "mąka pszenna chlebowa",
        "mąka żytnia typ 720", "mąka kukurydziana", "proszek do pieczenia", "soda oczyszczona",
        "kakao naturalne", "czekolada gorzka 70%", "czekolada mleczna", "czekolada biała",
        "cukier puder", "wafel ryżowy naturalny", "krakersy solone", "herbatniki maślane",
        "ciastka digestive", "czekoladowe groszki", "baton proteinowy czekoladowy",
        "chipsy ziemniaczane solne", "chrupki kukurydziane", "precle cienkie", "paluszki solone",
        "orzechy solone prażone", "migdały prażone", "kawa ziarnista arabica", "kawa mielona",
        "kawa rozpuszczalna", "herbata czarna earl grey", "herbata zielona", "herbata owocowa malinowa",
        "napój gazowany cola", "napój izotoniczny pomarańczowy", "woda kokosowa",
        "sok malinowy tłoczony", "syrop klonowy", "bulion warzywny kostka", "bulion drobiowy kostka",
        "przyprawa warzywna knorr", "przyprawa do zup", "konserwy mięsne wołowe", "pasztet drobiowy",
        "wątróbka drobiowa", "ser twarogowy półtłusty", "philadelphia naturalny", "ricotta",
        "halloumi", "paneer", "jajka rozmiar L", "jajka ekologiczne", "jajka przepiórcze",
        "tofu naturalne", "tofu wędzone", "tempeh", "seitan", "quinoa biała", "amarantus",
        "komosa ryżowa czerwona", "komosa biała", "bulgur", "kuskus", "pasta miso",
        "sos sojowy jasny", "sos teriyaki", "wasabi pasta", "nori arkusze", "imbir marynowany",
        "kimchi kapusta", "kapusta kiszona", "zakwas żurek", "pierogi z mięsem", "pierogi z serem",
        "pierogi z kapustą", "uszka do barszczu", "knedle ziemniaczane", "kopytka",
        "placki ziemniaczane mrożone", "bigos domowy", "gulasz wołowy", "sos pieczarkowy",
        "sos czosnkowy gotowy", "sos śmietanowy", "śledzie w occie", "śledzie w oleju",
        "rolada śledziowa", "sałatka jarzynowa", "sałatka gyros", "hummus klasyczny",
        "pesto zielone", "tapenada oliwkowa", "guacamole", "salsa pomidorowa", "tzatziki",
        "labneh", "oliwki zielone bez pestki", "oliwki czarne bez pestki", "kapary w occie",
        "suszone pomidory w oleju", "suszone figi", "suszone morele", "rodzynki sułtanki",
        "żurawina suszona", "daktyle świeże", "figi świeże", "mango", "papaja", "marakuja",
        "guawa", "lichi", "kumkwat", "rambutan", "durian", "dragon fruit", "awokado hass",
        "kardamon mielony", "goździki całe", "cynamon mielony", "gałka muszkatołowa",
        "anyż gwiaździsty", "ziele angielskie", "liść laurowy", "piment mielony",
        "kozieradka mielona", "estragon suszony", "lubczyk suszony", "hyzop", "szałwia",
        "melisa suszona", "mięta suszona", "werbena cytrynowa", "rumianek kwiaty",
        "koper włoski nasiona", "fenkuł nasiona", "anyż nasiona", "koperczyk", "czarnuszka",
        "len mielony", "sezam biały", "mak niebieski", "konopie łuskane", "ostropest plamisty"
    )
}
