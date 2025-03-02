@file:Suppress(	
            "NOTHING_TO_INLINE",
            "ObjectPropertyName",
            "UNUSED_ANONYMOUS_PARAMETER",
            "UnnecessaryVariable",
            "TopLevelPropertyNaming",
            "UNNECESSARY_SAFE_CALL",
            "RedundantNullableReturnType",
            "KotlinConstantConditions",
            "USELESS_ELVIS",
)
@file:OptIn(KtGramInternal::class)

package eu.vendeli.tgbot.generated

import eu.vendeli.tgbot.annotations.`internal`.KtGramInternal
import eu.vendeli.tgbot.types.`internal`.CommonMatcher
import eu.vendeli.tgbot.types.`internal`.InvocationMeta
import eu.vendeli.tgbot.types.`internal`.UpdateType
import eu.vendeli.tgbot.types.`internal`.configuration.RateLimits
import eu.vendeli.tgbot.utils.Invocable
import eu.vendeli.tgbot.utils.InvocationLambda
import eu.vendeli.tgbot.utils.getInstance
import kotlin.Any
import kotlin.OptIn
import kotlin.Pair
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map

private inline fun suspendCall(noinline block: InvocationLambda): InvocationLambda = block

private inline fun suspendCall(meta: InvocationMeta, noinline block: InvocationLambda): Invocable = block to meta

private val __TG_COMMANDS0: Map<Pair<String, UpdateType>, Invocable> = mapOf(
("/start" to UpdateType.MESSAGE) to suspendCall(
    	InvocationMeta(
    	qualifier = "com.example.blank.controller.StartController",
    	function = "start"
    	)
    ) { classManager, update, user, bot, parameters ->
      val inst = classManager.getInstance<com.example.blank.controller.StartController>()!!
      val param0 = update
      val param1 = bot
      val param2 = user!!

      if (
      	bot.update.userClassSteps[user.id] != "com.example.blank.controller.StartController"
      ) eu.vendeli.tgbot.generated.____clearClassData(user.id)

      com.example.blank.controller.StartController::start.invoke(
      	inst, param0, param1, param2
      )
    }
    ,
("rules" to UpdateType.CALLBACK_QUERY) to suspendCall(
    	InvocationMeta(
    	qualifier = "com.example.blank.controller.StartController",
    	function = "rules"
    	)
    ) { classManager, update, user, bot, parameters ->
      val inst = classManager.getInstance<com.example.blank.controller.StartController>()!!
      val param0 = update
      val param1 = bot
      val param2 = user!!

      if (
      	bot.update.userClassSteps[user.id] != "com.example.blank.controller.StartController"
      ) eu.vendeli.tgbot.generated.____clearClassData(user.id)

      com.example.blank.controller.StartController::rules.invoke(
      	inst, param0, param1, param2
      )
    }
    ,
)

private val __TG_INPUTS0: Map<String, Invocable> = mapOf(
    )

private val __TG_COMMONS0: Map<CommonMatcher, Invocable> = mapOf(
    )

private val __TG_UPDATE_TYPES0: Map<UpdateType, InvocationLambda> = mapOf(
    )

private val __TG_UNPROCESSED0: InvocationLambda? = null

internal val __ACTIVITIES: Map<String, List<Any?>> =
    mapOf("default" to listOf(__TG_COMMANDS0, __TG_INPUTS0,   __TG_COMMONS0, __TG_UPDATE_TYPES0, __TG_UNPROCESSED0),)
