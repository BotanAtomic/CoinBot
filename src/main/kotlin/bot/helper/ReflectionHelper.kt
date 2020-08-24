package bot.helper

import com.google.common.reflect.ClassPath


inline fun <reified T> loadClasses(packageName: String, annotation: Class<out Annotation>): List<T> {
    val classPath = ClassPath.from(ClassLoader.getSystemClassLoader())

    return classPath.getTopLevelClasses(packageName).filter {
        it.load().getAnnotationsByType(annotation) != null
    }.map { it.load().getConstructor().newInstance() as T }.toList()
}