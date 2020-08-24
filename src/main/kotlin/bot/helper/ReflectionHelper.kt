package bot.helper

import com.google.common.reflect.ClassPath
import kotlin.reflect.KClass


inline fun <reified T> loadClasses(
    packageName: String,
    annotation: KClass<out Annotation>,
    vararg args: Any?
): List<T> {
    val classPath = ClassPath.from(ClassLoader.getSystemClassLoader())

    return classPath.getTopLevelClasses(packageName).filter {
        it.load().getAnnotationsByType(annotation.java).isNotEmpty()
    }.map {
        val constructor = it.load().constructors[0]
        if (constructor.parameterCount > 0)
            it.load().constructors[0].newInstance(*args) as T
        else
            it.load().constructors[0].newInstance() as T
    }.toList()
}

fun getClasses(packageName: String, annotation: KClass<out Annotation>): List<Class<*>> {
    val classPath = ClassPath.from(ClassLoader.getSystemClassLoader())
    return classPath.getTopLevelClasses(packageName).filter {
        it.load().getAnnotationsByType(annotation.java).isNotEmpty()
    }.map { it.load() }
}

