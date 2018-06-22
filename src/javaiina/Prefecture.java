
/* Prefecture.java */

package javaiina;

import java.util.Map;

public final class Prefecture
{
    public static final Map<Integer, String> JapanPrefectures = Map.ofEntries(
        Map.entry(1, "Hokkaido"),
        Map.entry(2, "Aomori"),
        Map.entry(3, "Iwate"),
        Map.entry(4, "Miyagi"),
        Map.entry(5, "Akita"),
        Map.entry(6, "Yamagata"),
        Map.entry(7, "Fukushima"),
        Map.entry(8, "Ibaraki"),
        Map.entry(9, "Tochigi"),
        Map.entry(10, "Gumma"),
        Map.entry(11, "Saitama"),
        Map.entry(12, "Chiba"),
        Map.entry(13, "Tokyo"),
        Map.entry(14, "Kanagawa"),
        Map.entry(15, "Niigata"),
        Map.entry(16, "Toyama"),
        Map.entry(17, "Ishikawa"),
        Map.entry(18, "Fukui"),
        Map.entry(19, "Yamanashi"),
        Map.entry(20, "Nagano"),
        Map.entry(21, "Gifu"),
        Map.entry(22, "Shizuoka"),
        Map.entry(23, "Aichi"),
        Map.entry(24, "Mie"),
        Map.entry(25, "Shiga"),
        Map.entry(26, "Kyoto"),
        Map.entry(27, "Osaka"),
        Map.entry(28, "Hyogo"),
        Map.entry(29, "Nara"),
        Map.entry(30, "Wakayama"),
        Map.entry(31, "Tottori"),
        Map.entry(32, "Shimane"),
        Map.entry(33, "Okayama"),
        Map.entry(34, "Hiroshima"),
        Map.entry(35, "Yamaguchi"),
        Map.entry(36, "Tokushima"),
        Map.entry(37, "Kagawa"),
        Map.entry(38, "Ehime"),
        Map.entry(39, "Kochi"),
        Map.entry(40, "Fukuoka"),
        Map.entry(41, "Saga"),
        Map.entry(42, "Nagasaki"),
        Map.entry(43, "Kumamoto"),
        Map.entry(44, "Oita"),
        Map.entry(45, "Miyazaki"),
        Map.entry(46, "Kagoshima"),
        Map.entry(47, "Okinawa")
    );
    
    private Prefecture()
    {
        /* Prefecture class cannot be instantiated */
    }
    
    public static int getPrefectureIdFromName(String prefectureName)
    {
        var prefectureEntry = Prefecture.JapanPrefectures.entrySet().stream()
            .filter(entry -> entry.getValue().equals(prefectureName))
            .findFirst();
        
        if (!prefectureEntry.isPresent())
            throw new IllegalArgumentException("prefectureName");
        
        return prefectureEntry.get().getKey();
    }
    
    public static String getPrefectureNameFromId(int prefectureId)
    {
        var prefectureEntry = Prefecture.JapanPrefectures.entrySet().stream()
            .filter(entry -> entry.getKey().intValue() == prefectureId)
            .findFirst();
        
        if (!prefectureEntry.isPresent())
            throw new IllegalArgumentException("prefectureId");
        
        return prefectureEntry.get().getValue();
    }
    
    public static String[] getPrefectureNames()
    {
        return Prefecture.JapanPrefectures.entrySet().stream()
            .sorted((prefecture0, prefecture1) -> prefecture0.getKey() - prefecture1.getKey())
            .map(prefectureEntry -> prefectureEntry.getValue())
            .toArray(String[]::new);
    }
}
