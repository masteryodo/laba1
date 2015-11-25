package laba1.utils;

public final class GenerateId
{

    /**
     * метод получения уникального id
     * @return текущее время в формате long
     */
    public static synchronized long getId()
    {
        return System.currentTimeMillis();
    }

    private GenerateId() {}
}
