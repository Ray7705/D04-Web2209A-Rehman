package utility.formatting;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Jared Chevalier
 */
public final class CurrencyHelper
{
    private CurrencyHelper()
    { }

    public static NumberFormat getLocalCurrencyFormatter()
    {
        return getCurrencyFormatter(Locale.getDefault());
    }

    public static NumberFormat getCurrencyFormatter(Locale locale)
    {
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        return format;
    }
}
