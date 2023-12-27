package edu.hw10.SupportClasses;

import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;

public record MyRecord(@Min(0) int x, @NotNull String string, @NotNull Boolean correct) {
}
