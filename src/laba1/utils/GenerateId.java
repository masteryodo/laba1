package laba1.utils;

public final class GenerateId
{
    public static synchronized long getId()
    {
        return System.currentTimeMillis();
    }

    private GenerateId() {}
}
