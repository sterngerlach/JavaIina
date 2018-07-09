
/* Prefecture.java */

package javaiina;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Prefecture
{
    public static final Map<Integer, String> JapanPrefectures = Stream.of(
        new SimpleEntry<>(1, "Hokkaido"),
        new SimpleEntry<>(2, "Aomori"),
        new SimpleEntry<>(3, "Iwate"),
        new SimpleEntry<>(4, "Miyagi"),
        new SimpleEntry<>(5, "Akita"),
        new SimpleEntry<>(6, "Yamagata"),
        new SimpleEntry<>(7, "Fukushima"),
        new SimpleEntry<>(8, "Ibaraki"),
        new SimpleEntry<>(9, "Tochigi"),
        new SimpleEntry<>(10, "Gumma"),
        new SimpleEntry<>(11, "Saitama"),
        new SimpleEntry<>(12, "Chiba"),
        new SimpleEntry<>(13, "Tokyo"),
        new SimpleEntry<>(14, "Kanagawa"),
        new SimpleEntry<>(15, "Niigata"),
        new SimpleEntry<>(16, "Toyama"),
        new SimpleEntry<>(17, "Ishikawa"),
        new SimpleEntry<>(18, "Fukui"),
        new SimpleEntry<>(19, "Yamanashi"),
        new SimpleEntry<>(20, "Nagano"),
        new SimpleEntry<>(21, "Gifu"),
        new SimpleEntry<>(22, "Shizuoka"),
        new SimpleEntry<>(23, "Aichi"),
        new SimpleEntry<>(24, "Mie"),
        new SimpleEntry<>(25, "Shiga"),
        new SimpleEntry<>(26, "Kyoto"),
        new SimpleEntry<>(27, "Osaka"),
        new SimpleEntry<>(28, "Hyogo"),
        new SimpleEntry<>(29, "Nara"),
        new SimpleEntry<>(30, "Wakayama"),
        new SimpleEntry<>(31, "Tottori"),
        new SimpleEntry<>(32, "Shimane"),
        new SimpleEntry<>(33, "Okayama"),
        new SimpleEntry<>(34, "Hiroshima"),
        new SimpleEntry<>(35, "Yamaguchi"),
        new SimpleEntry<>(36, "Tokushima"),
        new SimpleEntry<>(37, "Kagawa"),
        new SimpleEntry<>(38, "Ehime"),
        new SimpleEntry<>(39, "Kochi"),
        new SimpleEntry<>(40, "Fukuoka"),
        new SimpleEntry<>(41, "Saga"),
        new SimpleEntry<>(42, "Nagasaki"),
        new SimpleEntry<>(43, "Kumamoto"),
        new SimpleEntry<>(44, "Oita"),
        new SimpleEntry<>(45, "Miyazaki"),
        new SimpleEntry<>(46, "Kagoshima"),
        new SimpleEntry<>(47, "Okinawa"))
        .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    
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
