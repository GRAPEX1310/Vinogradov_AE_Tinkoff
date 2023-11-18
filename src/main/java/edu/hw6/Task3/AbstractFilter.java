package edu.hw6.Task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default Task3.CompositeFilter and(AbstractFilter extraFilter) {
        Task3.CompositeFilter compositeFilter = new Task3.CompositeFilter();
        compositeFilter.previousFilters.add(extraFilter);
        compositeFilter.previousFilters.add(this);

        return compositeFilter;
    }
}
