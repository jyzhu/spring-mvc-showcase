package org.springframework.samples.mvc.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Controller
@RequestMapping("/async/callable")
public class CallableController {


	@RequestMapping("/response-body")
	public @ResponseBody Callable<String> callable() {

		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Callable result";
			}
		};
	}

	@RequestMapping("/view")
	public Callable<String> callableWithView(final Model model) {

		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				model.addAttribute("foo", "bar");
				model.addAttribute("fruit", "apple");
				return "views/html";
			}
		};
	}

	@RequestMapping("/exception")
	public @ResponseBody Callable<String> callableWithException(
			final @RequestParam(required=false, defaultValue="true") boolean handled) {

		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				if (handled) {
					// see handleException method further below
					throw new IllegalStateException("Callable error");
				}
				else {
					throw new IllegalArgumentException("Callable error");
				}
			}
		};
	}

	@RequestMapping("/custom-timeout-handling")
	public @ResponseBody WebAsyncTask<String> callableWithCustomTimeoutHandling() {

		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Callable result";
			}
		};

		return new WebAsyncTask<String>(1000, callable);
	}
	
	@RequestMapping("/stream")
	public ResponseBodyEmitter handleStream() {
		final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		service.execute(() -> {
			for (int i = 0; i < 10; i ++) {
				try {
					emitter.send("Send event: " + i + ", ", MediaType.TEXT_PLAIN);
					
					Thread.sleep(1000);
				} catch (Exception e) {
					return;
				}
			}
			emitter.complete();
		}); 
		
		return emitter;
	}

	@ExceptionHandler
	@ResponseBody
	public String handleException(IllegalStateException ex) {
		return "Handled exception: " + ex.getMessage();
	}

}
