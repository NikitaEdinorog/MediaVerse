package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.util;

import java.util.Objects;

public final class ToStringBuilder {
    private static final char[] ASTERISKS = "********************".toCharArray();

    private final StringBuilder sb;

    private boolean hasAtLeastOneFieldAppended = false;


    public ToStringBuilder(Object object)
    {
        Objects.requireNonNull(object, "Object is null");

        this.sb = new StringBuilder(object.getClass().getSimpleName());
    }


    public <T> ToStringBuilder append(String fieldName, T value)
    {
        return append(fieldName, value, false);
    }


    public <T> ToStringBuilder append(String fieldName, T value, boolean isSecure)
    {
        appendFieldName(fieldName);

        if (isSecure)
        {
            appendSecure(value);
        }
        else
        {
            sb.append(value);
        }

        return this;
    }


    private void appendFieldName(String fieldName)
    {
        Objects.requireNonNull(fieldName, "fieldName is null");

        if (hasAtLeastOneFieldAppended)
        {
            sb.append(',');
        }
        else
        {
            sb.append(':');
            hasAtLeastOneFieldAppended = true;
        }

        sb.append(' ').append(fieldName).append('=');
    }


    private <T> void appendSecure(T value)
    {
        if (null == value)
        {
            sb.append((String) null);
            return;
        }

        int len = value.toString().length();

        if (len == 0)
        {
            return;
        }

        if (len > ASTERISKS.length)
        {
            sb.append(ASTERISKS);
        }
        else
        {
            sb.append(ASTERISKS, 0, len);
        }
    }


    @Override
    public String toString()
    {
        return sb.toString();
    }
}
