package cn.edu.seu.institute.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.seu.institute.entity.Admin;
import cn.edu.seu.institute.entity.Application;
import cn.edu.seu.institute.entity.Article;
import cn.edu.seu.institute.entity.File;
import cn.edu.seu.institute.service.AdminService;
import cn.edu.seu.institute.service.ApplicationService;
import cn.edu.seu.institute.service.ArticleService;
import cn.edu.seu.institute.service.FileService;
import cn.edu.seu.institute.view.velocity.ViewUtil;

@Controller
public class MainController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private FileService fileService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private ApplicationService applicationService;

	private static int PAGE_SIZE = 10;

	@RequestMapping(value = { "/", "/default.html" })
	public String index() {
		return "default";
	}

	@RequestMapping(value = "/apply.html")
	public String applyPage(Model model) {
		List<Article> recentNotice = articleService.getRecentAnnounce();
		model.addAttribute("recentNotice", recentNotice);
		List<File> recentFile = fileService.getRecentFile();
		model.addAttribute("recentFile", recentFile);
		return "apply";
	}

	@RequestMapping(value = "/apply.html", method = RequestMethod.POST)
	public void apply(Model model, @RequestParam("name") String name,
			@RequestParam("phone") String phone,
			@RequestParam("mail") String mail,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		if (file != null) {
			String path = request.getSession().getServletContext()
					.getRealPath("static/applyForms");
			String fileName = name + "-" + file.getOriginalFilename();

			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			String hashString = format.format(new Date());
			java.io.File directory = new java.io.File(path + "/" + name + "-"
					+ phone + "-" + hashString);
			if (!directory.exists() && !directory.isDirectory()) {
				directory.mkdir();
			}

			java.io.File targetFile = new java.io.File(path + "/" + name + "-"
					+ phone + "-" + hashString, fileName);

			if ((fileName.endsWith(".doc") || fileName.endsWith(".docx"))
					&& !targetFile.exists()) {
				// 保存
				try {
					file.transferTo(targetFile);
					Application application = new Application();
					application.setName(name);
					application.setPhone(phone);
					application.setMail(mail);
					application.setDirectory(name + "-" + phone + "-"
							+ hashString);
					application.setFilename(fileName);
					applicationService.insertApplication(application);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/apply.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/contact.html")
	public String contact(Model model) {
		List<Article> recentNotice = articleService.getRecentAnnounce();
		model.addAttribute("recentNotice", recentNotice);
		List<File> recentFile = fileService.getRecentFile();
		model.addAttribute("recentFile", recentFile);
		return "contact";
	}

	@RequestMapping(value = "/document.html", method = RequestMethod.GET)
	public String document(Model model, HttpServletRequest request) {
		String pageIndex = (String) request.getParameter("pn");
		int pn = pageIndexResolve(pageIndex);
		List<File> fileList = fileService.getFilesByPnAndSize(pn, PAGE_SIZE);

		List<Article> recentNotice = articleService.getRecentAnnounce();
		model.addAttribute("recentNotice", recentNotice);
		List<File> recentFile = fileService.getRecentFile();
		model.addAttribute("recentFile", recentFile);

		int count = fileService.getFileCount();
		int pageCount = (count - 1) / PAGE_SIZE + 1;

		model.addAttribute("pageIndex", pn);
		model.addAttribute("fileList", fileList);
		model.addAttribute("fileCount", count);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("url", "/document.html");

		return "document";
	}

	@RequestMapping(value = "/members.html")
	public String members(Model model) {
		List<Article> recentNotice = articleService.getRecentAnnounce();
		model.addAttribute("recentNotice", recentNotice);
		List<File> recentFile = fileService.getRecentFile();
		model.addAttribute("recentFile", recentFile);
		return "members";
	}

	@RequestMapping(value = "/notice.html", method = RequestMethod.GET)
	public String notice(Model model, HttpServletRequest request) {
		String pageIndex = (String) request.getParameter("pn");
		int pn = pageIndexResolve(pageIndex);
		List<Article> articleList = articleService.getCatArticlesByPnAndSize(0,
				pn, PAGE_SIZE);

		List<Article> recentNotice = articleService.getRecentAnnounce();
		model.addAttribute("recentNotice", recentNotice);
		List<File> recentFile = fileService.getRecentFile();
		model.addAttribute("recentFile", recentFile);

		int count = articleService.getArticleCountByCategory(0);
		int pageCount = (count - 1) / PAGE_SIZE + 1;

		model.addAttribute("pageIndex", pn);
		model.addAttribute("articleList", articleList);
		model.addAttribute("articleCount", count);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("url", "/notice.html");

		return "notice";
	}

	@RequestMapping(value = "/rules.html")
	public String rules(Model model) {
		List<Article> recentNotice = articleService.getRecentAnnounce();
		model.addAttribute("recentNotice", recentNotice);
		List<File> recentFile = fileService.getRecentFile();
		model.addAttribute("recentFile", recentFile);
		return "rules";
	}

	@RequestMapping(value = "/viewNotice-{id}.html")
	public String viewNotice(Model model, @PathVariable("id") int id,
			HttpServletResponse response) {
		Article article = articleService.getArticleByArticleId(id);
		List<Article> recentNotice = articleService.getRecentAnnounce();
		model.addAttribute("recentNotice", recentNotice);
		List<File> recentFile = fileService.getRecentFile();
		model.addAttribute("recentFile", recentFile);
		if (article != null) {
			model.addAttribute("notice", article);
			return "viewNotice";
		} else {
			try {
				response.sendRedirect(ViewUtil.CONTEXT_PATH + "/notice.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	private int pageIndexResolve(String pageIndex) {
		int index = 1;
		if (pageIndex != null && !pageIndex.trim().isEmpty()) {
			index = Integer.valueOf(pageIndex.trim());
		}
		return index;
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	private String loginPage(HttpServletResponse response) {
		Admin admin = adminService.currentAdmin();
		if (admin == null) {
			return "manage/login";
		} else {
			try {
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/admin/manage");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	private void login(HttpServletResponse response,
			@RequestParam("name") String name, @RequestParam("pswd") String pswd) {
		try {
			Admin admin = adminService.login(name, pswd);
			if (admin != null) {
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/admin/manage");
			} else {
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/admin/login");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/admin/manage", method = RequestMethod.GET)
	private String postArticlePage(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// 如果已登录
		if (adminService.currentAdmin() != null) {
			// articles
			String pageIndexA = (String) request.getParameter("pnA");
			int pnA = pageIndexResolve(pageIndexA);
			List<Article> articleList = articleService
					.getCatArticlesByPnAndSize(0, pnA, PAGE_SIZE);
			int countA = articleService.getArticleCountByCategory(0);
			int pageCountA = (countA - 1) / PAGE_SIZE + 1;

			model.addAttribute("pageIndexA", pnA);
			model.addAttribute("articleList", articleList);
			model.addAttribute("articleCount", countA);
			model.addAttribute("pageCountA", pageCountA);

			// file
			String pageIndex = (String) request.getParameter("pn");
			int pn = pageIndexResolve(pageIndex);
			List<File> fileList = fileService
					.getFilesByPnAndSize(pn, PAGE_SIZE);

			int count = fileService.getFileCount();
			int pageCount = (count - 1) / PAGE_SIZE + 1;

			model.addAttribute("pageIndex", pn);
			model.addAttribute("fileList", fileList);
			model.addAttribute("fileCount", count);
			model.addAttribute("pageCount", pageCount);

			// application
			String pageIndexB = (String) request.getParameter("pnB");
			int pnB = pageIndexResolve(pageIndexB);
			List<Application> fileListB = applicationService
					.getApplicationsByPnAndPageSize(pnB, PAGE_SIZE);

			int countB = applicationService.getApplicationCount();
			int pageCountB = (countB - 1) / PAGE_SIZE + 1;

			model.addAttribute("pageIndexB", pnB);
			model.addAttribute("fileListB", fileListB);
			model.addAttribute("fileCountB", countB);
			model.addAttribute("pageCountB", pageCountB);

			String tabType = (String) request.getParameter("tab");
			tabType = tabType == null || tabType.isEmpty() ? "1" : tabType;
			model.addAttribute("tabType", tabType);
			model.addAttribute("url", "/admin/manage");
			return "manage/manage";
		} else {
			try {
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/admin/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping(value = "/admin/deleteArticle-{id}")
	private void deleteArticle(@PathVariable("id") int id,
			HttpServletResponse response) {
		if (adminService.currentAdmin() == null) {
			try {
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/admin/login");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				articleService.archiveArticle(id);
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/admin/manage");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/admin/postArticle", method = RequestMethod.POST)
	private void postArticle(@RequestParam("title") String title,
			@RequestParam("content") String content,
			HttpServletResponse response) {
		if (adminService.currentAdmin() == null) {
			try {
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/admin/login");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		articleService.insertArticle(article);
		try {
			response.getWriter().write("1");
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
	private void upload(HttpServletRequest request,
			HttpServletResponse response, @RequestParam MultipartFile file) {
		if (adminService.currentAdmin() == null) {
			try {
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/admin/login");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file != null) {
			String path = request.getSession().getServletContext()
					.getRealPath("static/uploads");
			String fileName = file.getOriginalFilename();

			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			String hashString = format.format(new Date());
			java.io.File directory = new java.io.File(path + "/" + hashString);
			if (!directory.exists() && !directory.isDirectory()) {
				directory.mkdir();
			}

			java.io.File targetFile = new java.io.File(path + "/" + hashString,
					fileName);

			if (!fileName.endsWith(".exe") && !targetFile.exists()) {
				// 保存
				try {
					file.transferTo(targetFile);
					File fileEntity = new File();
					fileEntity.setDirectory(hashString);
					fileEntity.setFilename(fileName);
					fileService.uploadFile(fileEntity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/document.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/admin/deleteFile-{id}")
	private void deleteFile(@PathVariable("id") int id,
			HttpServletResponse response) {
		if (adminService.currentAdmin() == null) {
			try {
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/admin/login");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				fileService.deleteFile(id);
				response.sendRedirect(ViewUtil.getContextPath()
						+ "/admin/manage");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
